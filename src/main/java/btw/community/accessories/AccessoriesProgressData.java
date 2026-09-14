package btw.community.accessories;

import api.world.data.DataEntry;
import api.world.data.DataProvider;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

/** Persistent, synchronized progress for the accessory-capacity milestones. */
public final class AccessoriesProgressData {
    public static final int BASE_ACCESSORY_SLOTS = 6;
    public static final int MAX_ACCESSORY_SLOTS = 9;

    public static final DataEntry.PlayerDataEntry<Boolean> PORTAL_SLOT_UNLOCKED = playerFlag("AccessoriesPortalSlot");
    public static final DataEntry.PlayerDataEntry<Boolean> WITHER_SLOT_UNLOCKED = playerFlag("AccessoriesWitherSlot");
    public static final DataEntry.PlayerDataEntry<Boolean> DRAGON_SLOT_UNLOCKED = playerFlag("AccessoriesDragonSlot");

    private AccessoriesProgressData() {}

    public static void register() {
        PORTAL_SLOT_UNLOCKED.register();
        WITHER_SLOT_UNLOCKED.register();
        DRAGON_SLOT_UNLOCKED.register();
    }

    public static int getAccessorySlotCount(EntityPlayer player) {
        int slots = BASE_ACCESSORY_SLOTS;
        if (player.getData(PORTAL_SLOT_UNLOCKED)) slots++;
        if (player.getData(WITHER_SLOT_UNLOCKED)) slots++;
        if (player.getData(DRAGON_SLOT_UNLOCKED)) slots++;
        return slots;
    }

    private static DataEntry.PlayerDataEntry<Boolean> playerFlag(String name) {
        return DataProvider.getBuilder(Boolean.class)
                .name(name)
                .defaultSupplier(() -> false)
                .readNBT(NBTTagCompound::getBoolean)
                .writeNBT(NBTTagCompound::setBoolean)
                .player()
                .syncPlayer()
                .buildPlayer();
    }
}
