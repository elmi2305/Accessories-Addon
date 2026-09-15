package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.ContainerAccessories;
import net.fabricmc.accessories.GuiAccessories;
import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.AccessoryAppearanceSync;
import net.fabricmc.accessories.client.AccessoryAppearanceCache;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin {

    @Inject(method = "handleCustomPayload", at = @At("HEAD"), cancellable = true)
    private void accessories$appearance(Packet250CustomPayload packet, CallbackInfo ci) {
        if (AccessoryAppearanceSync.CHANNEL.equals(packet.channel)) {
            AccessoryAppearanceCache.accept(packet);
            ci.cancel();
        }
    }

    @Inject(method = {"cleanup", "handleLogin"}, at = @At("HEAD"))
    private void accessories$resetAppearance(CallbackInfo ci) {
        AccessoryAppearanceCache.clear();
    }

    @Inject(method = "handleRespawn", at = @At("HEAD"))
    private void accessories$respawnAppearance(Packet9Respawn packet, CallbackInfo ci) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if (player == null || player.dimension != packet.respawnDimension) {
            AccessoryAppearanceCache.clear();
        } else {
            // same-world respawns retain other players, who will not be tracked anew.
            AccessoryAppearanceCache.remove(player.entityId);
        }
    }

    @Inject(method = "handleDestroyEntity", at = @At("HEAD"))
    private void accessories$removeAppearance(Packet29DestroyEntity packet, CallbackInfo ci) {
        for (int entityId : packet.entityId) AccessoryAppearanceCache.remove(entityId);
    }

    @Inject(method = "handleOpenWindow", at = @At("HEAD"), cancellable = true)
    private void onHandleOpenWindow(Packet100OpenWindow packet, CallbackInfo ci) {
        if (packet.inventoryType == 42) { // our custom GUI ID
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            ContainerAccessories container = new ContainerAccessories(
                    player.inventory,
                    ((IPlayerAccessories) player).getAccessoryInventory(),
                    player,
                    packet.slotsCount
            );
            // Click packets are accepted only when this exactly matches the
            // container window ID created by the server.
            container.windowId = packet.windowId;
            player.openContainer = container;
            Minecraft.getMinecraft().displayGuiScreen(new GuiAccessories(player, container));
            ci.cancel();
        }
    }
}
