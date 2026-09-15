package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.AccessoryAppearanceSync;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityTrackerEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityTrackerEntry.class)
public abstract class EntityTrackerEntryMixin {
    @Shadow public Entity myEntity;

    @Inject(method = "tryStartWachingThis", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/NetServerHandler;sendPacketToPlayer(Lnet/minecraft/src/Packet;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void accessories$startTracking(EntityPlayerMP viewer, CallbackInfo ci) {
        if (myEntity instanceof EntityPlayer) {
            viewer.playerNetServerHandler.sendPacketToPlayer(AccessoryAppearanceSync.packet((EntityPlayer) myEntity));
        }
    }
}
