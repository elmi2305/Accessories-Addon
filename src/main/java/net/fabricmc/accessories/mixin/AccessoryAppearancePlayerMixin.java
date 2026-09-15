package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.AccessoryAppearanceSync;
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

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void accessories$syncAppearance(CallbackInfo ci) {
        EntityPlayerMP player = (EntityPlayerMP) (Object) this;
        byte[] current = AccessoryAppearanceSync.snapshot(player);
        if (player.worldObj != accessories$lastWorld || !Arrays.equals(current, accessories$lastAppearance)) {
            ((WorldServer) player.worldObj).getEntityTracker().sendPacketToAllAssociatedPlayers(player,
                    new Packet250CustomPayload(AccessoryAppearanceSync.CHANNEL, current));
            accessories$lastAppearance = current;
            accessories$lastWorld = player.worldObj;
        }
    }
}
