package btw.community.accessories;

import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.AccessoryAppearanceSync;
import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.IInventory;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet250CustomPayload;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class ACUtils {

    public static final String NBT_ACCESSORY_TAG = "Accessories"; // unused field
    public static final int MAX_ACCESSORY_SLOTS = 5; // unused field

    public static ItemStack getAccessoryAt(EntityPlayer player, int slot) {
        if (player instanceof IPlayerAccessories) {
            ItemStack[] accessories = ((IPlayerAccessories) player).getAccessories();
            if (slot >= 0 && slot < accessories.length) {
                return accessories[slot];
            }
        }
        return null;
    }


    public static boolean hasAccessory(EntityPlayer player, Item item) {
        if (player instanceof IPlayerAccessories) {
            for (ItemStack stack : ((IPlayerAccessories) player).getAccessories()) {
                if (stack != null && stack.getItem() == item) {
                    return true;
                }
            }
        }
        return false;
    }
    private static ItemStack[] getAccessoriesArray(EntityPlayer player) {
        if (player instanceof IPlayerAccessories) {
            ItemStack[] arr = ((IPlayerAccessories) player).getAccessories();
            return arr == null ? new ItemStack[0] : arr;
        }
        return new ItemStack[0];
    }


    public static boolean hasAnyAccessory(EntityPlayer player, Item... items) {
        if (items == null || items.length == 0) return false;
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack == null) continue;
            Item it = stack.getItem();
            for (Item candidate : items) {
                if (candidate != null && it == candidate) return true;
            }
        }
        return false;
    }


    public static ItemStack findAccessory(EntityPlayer player, java.util.function.Predicate<ItemStack> condition) {
        if (condition == null) return null;
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack != null && condition.test(stack)) return stack;
        }
        return null;
    }


    public static java.util.List<ItemStack> getAllAccessories(EntityPlayer player) {
        java.util.List<ItemStack> out = new java.util.ArrayList<>();
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack != null) out.add(stack);
        }
        return out;
    }
    public static java.util.List<ItemStack> getAllAccessoriesWithCooldowns(EntityPlayer player) {
        java.util.List<ItemStack> out = new java.util.ArrayList<>();
        for (ItemStack stack : getAllAccessories(player)) {
            if (hasCooldown(stack)) out.add(stack);
        }
        return out;
    }

    public static boolean hasCooldown(ItemStack stack) {
        if (stack == null) return false;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return false;
        return ((AccessoryItem) it).getFinalCooldown() >= 0;
    }

    public static boolean hasCooldownItem(Item item) {
        if (!(item instanceof AccessoryItem)) return false;
        return ((AccessoryItem) item).getFinalCooldown() >= 0;
    }


    public static void decrementAllAccessoryCooldowns(EntityPlayer player) {
        for (ItemStack stack : getAllAccessoriesWithCooldowns(player)) {
            Item it = stack.getItem();
            if (!(it instanceof AccessoryItem)) continue;
            AccessoryItem accessory = (AccessoryItem) it;
            int cd = accessory.getCooldown();
            if (cd > 0) accessory.setCooldown(cd - 1);
        }
    }


    public static void resetAccessoryCooldown(EntityPlayer player, Item accessoryItem) {
        if (accessoryItem == null) return;
        ItemStack stack = findAccessory(player, s -> s.getItem() == accessoryItem);
        if (stack == null) return;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return;
        AccessoryItem acc = (AccessoryItem) it;
        setAccessoryCooldown(player, acc, acc.getFinalCooldown());
    }

    public static void setAccessoryCooldown(EntityPlayer player, AccessoryItem accessory, int cooldown) {
        accessory.setCooldown(cooldown);
        syncAccessoryCooldown(player, accessory);
    }


    public static void syncAccessoryCooldown(EntityPlayer player, AccessoryItem accessory) {
        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP) player).playerNetServerHandler.sendPacketToPlayer(createCooldownSyncPacket(player, accessory));
        }
    }

    public static Packet250CustomPayload createCooldownSyncPacket(EntityPlayer player) {
        List<ItemStack> stacks = getAllAccessoriesWithCooldowns(player);
        ByteBuffer data = ByteBuffer.allocate(1 + stacks.size() * 8);
        Set<Integer> writtenItems = new HashSet<Integer>();

        int countPosition = data.position();
        data.put((byte) 0);
        int count = 0;
        for (ItemStack stack : stacks) {
            if (!(stack.getItem() instanceof AccessoryItem accessory) || !writtenItems.add(stack.itemID)) continue;
            data.putInt(stack.itemID);
            data.putInt(accessory.getCooldown());
            count++;
        }
        data.put(countPosition, (byte) count);

        byte[] payload = new byte[data.position()];
        data.flip();
        data.get(payload);
        return new Packet250CustomPayload("accessories|CD", payload);
    }

    public static void applyCooldownSyncPacket(Packet250CustomPayload packet) {
        if (packet.data == null || packet.length != packet.data.length || packet.data.length < 1) return;

        ByteBuffer data = ByteBuffer.wrap(packet.data);
        int count = data.get() & 255;
        if (count > (packet.data.length - 1) / 8 || data.remaining() != count * 8) return;

        for (int i = 0; i < count; i++) {
            int itemId = data.getInt();
            int cooldown = data.getInt();
            if (itemId < 0 || itemId >= Item.itemsList.length || !(Item.itemsList[itemId] instanceof AccessoryItem accessory)) continue;
            accessory.setCooldown(Math.max(0, Math.min(cooldown, accessory.getFinalCooldown())));
        }
    }

    public static void applyLocalAccessoryAppearance(Packet250CustomPayload packet) {
        if (packet.data == null || packet.length != AccessoryAppearanceSync.PAYLOAD_SIZE || packet.data.length != AccessoryAppearanceSync.PAYLOAD_SIZE) return;

        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (!(player instanceof IPlayerAccessories)) return;

        ByteBuffer data = ByteBuffer.wrap(packet.data);
        if (data.getInt() != player.entityId) return;
        int activeSlots = data.get() & 255;
        if (activeSlots > AccessoryAppearanceSync.SLOT_COUNT) return;

        IInventory inventory = ((IPlayerAccessories) player).getAccessoryInventory();
        for (int slot = 0; slot < AccessoryAppearanceSync.SLOT_COUNT; slot++) {
            int itemId = data.getInt();
            ItemStack stack = slot < activeSlots && itemId >= 0 && itemId < Item.itemsList.length && Item.itemsList[itemId] != null
                    ? new ItemStack(itemId, 1, 0)
                    : null;
            inventory.setInventorySlotContents(slot, stack);
        }
        ((IPlayerAccessories) player).updateAccessoriesFromInventory(inventory);
    }

    private static Packet250CustomPayload createCooldownSyncPacket(EntityPlayer player, AccessoryItem accessory) {
        if (!hasAccessory(player, accessory)) return createCooldownSyncPacket(player);
        ByteBuffer data = ByteBuffer.allocate(9);
        data.put((byte) 1);
        data.putInt(accessory.itemID);
        data.putInt(accessory.getCooldown());
        return new Packet250CustomPayload("accessories|CD", data.array());
    }

    public static int getActiveCooldown(EntityPlayer player, Item accessoryItem) {
        if (accessoryItem == null) return -1;
        ItemStack stack = findAccessory(player, s -> s.getItem() == accessoryItem);
        if (stack == null) return -1;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return -1;
        return ((AccessoryItem) it).getCooldown();
    }

    public static boolean isAccessoryReady(EntityPlayer player, AccessoryItem accessoryItem) {
        if (accessoryItem == null) return false;
        return hasAccessory(player, accessoryItem) && hasCooldownItem(accessoryItem) && accessoryItem.getCooldown() == 0;
    }


}
