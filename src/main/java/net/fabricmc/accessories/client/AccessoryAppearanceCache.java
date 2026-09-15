package net.fabricmc.accessories.client;

import net.fabricmc.accessories.AccessoryAppearanceSync;
import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.Item;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.World;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public final class AccessoryAppearanceCache {
    private static final Map<Integer, int[]> APPEARANCES = new HashMap<>();
    private static World world;

    private AccessoryAppearanceCache() {}

    public static void clear() {
        APPEARANCES.clear();
        world = null;
    }

    private static void checkWorld() {
        World current = Minecraft.getMinecraft().theWorld;
        if (world != current) {
            clear();
            world = current;
        }
    }

    public static void remove(int entityId) {
        APPEARANCES.remove(entityId);
    }

    public static int[] get(int entityId) {
        checkWorld();
        return APPEARANCES.get(entityId);
    }

    public static void accept(Packet250CustomPayload packet) {
        checkWorld();
        if (world == null || packet.data == null || packet.length != AccessoryAppearanceSync.PAYLOAD_SIZE || packet.data.length != AccessoryAppearanceSync.PAYLOAD_SIZE) return;
        ByteBuffer data = ByteBuffer.wrap(packet.data);
        int entityId = data.getInt();
        int active = data.get() & 255;
        if (active > AccessoryAppearanceSync.SLOT_COUNT) return;
        int[] ids = new int[AccessoryAppearanceSync.SLOT_COUNT];
        for (int slot = 0; slot < ids.length; slot++) {
            int id = data.getInt();
            if (id != -1 && (slot >= active || id < 0 || id >= Item.itemsList.length || !(Item.itemsList[id] instanceof AccessoryItem))) return;
            ids[slot] = id;
        }
        APPEARANCES.put(entityId, ids);
    }
}
