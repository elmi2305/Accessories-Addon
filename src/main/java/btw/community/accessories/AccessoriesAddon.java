package btw.community.accessories;


import api.AddonHandler;
import api.BTWAddon;
import api.config.AddonConfig;
import net.fabricmc.accessories.ContainerAccessories;
import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

import java.io.*;
import java.util.*;

public class AccessoriesAddon extends BTWAddon {
    // Packet250 channel names are capped at 20 characters in this Minecraft version.
    public static final String OPEN_ACCESSORIES_CHANNEL = "accessories|OA";

    private static AccessoriesAddon instance;

    public AccessoriesAddon() {
        super();
    }

    public static AccessoriesAddon getInstance() {
        if (instance == null) {
            instance = new AccessoriesAddon();
        }
        instance.modID = "accessories";
        return instance;
    }


    @Override
    public void initialize() {
        AddonHandler.logMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
        AccessoriesProgressData.register();
        ACItems.initItems();
        ACRecipes.initRecipes();

        // The handler is registered on both physical sides. Only a server player can
        // open the authoritative container; a client receives the resulting
        // Packet100OpenWindow in NetClientHandlerMixin.
        this.registerPacketHandler(OPEN_ACCESSORIES_CHANNEL, (packet, player) -> {
            if (player instanceof EntityPlayerMP) {
                openAccessoriesFor((EntityPlayerMP) player);
            }
        });
    }

    /** Sends a client-to-server request. The client must not create this container itself. */
    public static void requestOpenAccessories(EntityClientPlayerMP player) {
        player.sendQueue.addToSendQueue(new Packet250CustomPayload(OPEN_ACCESSORIES_CHANNEL, new byte[0]));
    }

    private static void openAccessoriesFor(EntityPlayerMP player) {
        int windowId = player.incrementAndGetWindowID();
        IInventory accessoryInventory = ((IPlayerAccessories) player).getAccessoryInventory();

        player.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(
                windowId,
                42,
                "Accessories",
                AccessoriesProgressData.getAccessorySlotCount(player),
                accessoryInventory.isInvNameLocalized()
        ));

        player.openContainer = new ContainerAccessories(player.inventory, accessoryInventory, player,
                AccessoriesProgressData.getAccessorySlotCount(player));
        player.openContainer.windowId = windowId;
        player.openContainer.onCraftGuiOpened(player);
    }

    public void displayGUIAccessories(EntityPlayerMP p, IInventory accessoryInventory) {
        // Kept as the single server-side entry point for any future callers.
        if (p != null) {
            openAccessoriesFor(p);
        }
    }


//    private static Packet250CustomPayload createOpenMenuPacket(EntityPlayer player) {
//        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//        DataOutputStream dataStream = new DataOutputStream(byteStream);
//
//        try {
//            dataStream.writeInt(player.entityId);       // horse entity ID
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return new Packet250CustomPayload("nm|HorseProg", byteStream.toByteArray());
//    }
//
//    public static void sendHorseProgressToAll(EntityHorse horse, int progress) {
//        Packet250CustomPayload packet = createHorseProgressPacket(horse.entityId, progress);
//        for (Object playerObj : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
//            if (playerObj instanceof EntityPlayerMP player && player.ridingEntity instanceof EntityHorse) {
//                if (player.getDistanceSqToEntity(horse) < 8 * 8) {
//                    player.playerNetServerHandler.sendPacketToPlayer(packet);
//                }
//            }
//        }
//    }



    public KeyBinding accessoryKeyBind;
    public static String accessoryKey;

    @Override
    public void preInitialize() {
        super.preInitialize();
    }

    @Override
    public void registerConfigProperties(AddonConfig config) {
        super.registerConfigProperties(config);
        config.registerString("AccessoryAbilityKey", "Y");

    }

    @Override
    public void handleConfigProperties(AddonConfig config) {
        super.handleConfigProperties(config);
        accessoryKey = config.getString("AccessoryAbilityKey"); // unused key
    }

    public void initKeybind(){
        accessoryKeyBind = new KeyBinding("Accessory Ability", Keyboard.getKeyIndex(accessoryKey));

        GameSettings settings = Minecraft.getMinecraft().gameSettings;
        KeyBinding[] keyBindings = settings.keyBindings;
        keyBindings = Arrays.copyOf(keyBindings, keyBindings.length + 1);
        keyBindings[keyBindings.length - 1] = accessoryKeyBind;
        settings.keyBindings = keyBindings;
    }
    public void modifyConfigProperty(String propertyName, String newValue) {
        String filename = "config/" + "accessories" + ".properties";
        File config = new File(filename);

        // Ensure the file exists before modifying
        if (!config.exists()) {
            System.out.println("Config file does not exist. Creating a new one.");
            try {
                config.getParentFile().mkdirs(); // Ensure config directory exists
                config.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        List<String> lines = new ArrayList<>();
        boolean propertyFound = false;

        // Read the config file and modify the specified property
        try (BufferedReader reader = new BufferedReader(new FileReader(config))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(propertyName + "=")) {
                    lines.add(propertyName + "=" + newValue); // Modify specified property
                    propertyFound = true;
                } else {
                    lines.add(line); // Keep other settings unchanged
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // If the property wasn't found, add it at the end
        if (!propertyFound) {
            lines.add(propertyName + "=" + newValue);
        }

        // Write back the modified config file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(config, false))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
