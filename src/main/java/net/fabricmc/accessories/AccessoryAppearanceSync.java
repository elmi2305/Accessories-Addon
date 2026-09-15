package net.fabricmc.accessories;

import btw.community.accessories.AccessoriesProgressData;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Packet250CustomPayload;

import java.nio.ByteBuffer;

public final class AccessoryAppearanceSync {
    public static final String CHANNEL = "accessories|AP";
    public static final int SLOT_COUNT = AccessoriesProgressData.MAX_ACCESSORY_SLOTS;
    public static final int PAYLOAD_SIZE = 5 + SLOT_COUNT * 4;

    private AccessoryAppearanceSync() {}

    public static byte[] snapshot(EntityPlayer player) {
        ItemStack[] equipped = ((IPlayerAccessories) player).getAccessories();
        int active = Math.min(equipped.length, SLOT_COUNT);
        ByteBuffer data = ByteBuffer.allocate(PAYLOAD_SIZE);
        data.putInt(player.entityId).put((byte) active);
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            ItemStack stack = slot < active ? equipped[slot] : null;
            data.putInt(stack == null ? -1 : stack.itemID);
        }
        return data.array();
    }

    public static Packet250CustomPayload packet(EntityPlayer player) {
        return new Packet250CustomPayload(CHANNEL, snapshot(player));
    }
}
