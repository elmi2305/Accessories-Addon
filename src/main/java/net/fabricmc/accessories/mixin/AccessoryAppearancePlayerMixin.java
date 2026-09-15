package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.AccessoryAppearanceSync;
import btw.community.accessories.ACUtils;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Packet250CustomPayload;
import net.minecraft.src.World;
import net.minecraft.src.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(EntityPlayerMP.class)
public abstract class AccessoryAppearancePlayerMixin {
    @Unique private byte[] accessories$lastAppearance;
    @Unique private World accessories$lastWorld;
    @Unique private boolean accessories$sentCooldownSnapshot;

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void accessories$syncAppearance(CallbackInfo ci) {
        EntityPlayerMP player = (EntityPlayerMP) (Object) this;
        byte[] current = AccessoryAppearanceSync.snapshot(player);
        boolean appearanceChanged = player.worldObj != accessories$lastWorld || !Arrays.equals(current, accessories$lastAppearance);

        if (appearanceChanged) {
            ((WorldServer) player.worldObj).getEntityTracker().sendPacketToAllAssociatedPlayers(player,
                    new Packet250CustomPayload(AccessoryAppearanceSync.CHANNEL, current));
            accessories$lastAppearance = current;
            accessories$lastWorld = player.worldObj;
        }

        if (!accessories$sentCooldownSnapshot || appearanceChanged) {
            player.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload(AccessoryAppearanceSync.CHANNEL, current));
            player.playerNetServerHandler.sendPacketToPlayer(ACUtils.createCooldownSyncPacket(player));
            accessories$sentCooldownSnapshot = true;
        }
    }
}
