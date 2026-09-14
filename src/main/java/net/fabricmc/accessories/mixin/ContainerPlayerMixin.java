package net.fabricmc.accessories.mixin;

import btw.inventory.container.PlayerContainer;
import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.SlotAccessory;
import net.minecraft.src.*;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container {
    @Shadow public EntityPlayer thePlayer;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(InventoryPlayer inventory, boolean isLocal, EntityPlayer player, CallbackInfo ci) {
//        if(player.capabilities.isCreativeMode) return;
//        IInventory accessoryInv = ((IPlayerAccessories) player).getAccessoryInventory();
//
//        for (int i = 0; i < 6; ++i) {
//            this.addSlotToContainer(new SlotAccessory(accessoryInv, i, 80 + 100, 13 + i * 25));
//        }
    }

//    @Inject(method = "onContainerClosed", at = @At("HEAD"))
//    private void onClose(EntityPlayer player, CallbackInfo ci) {
//        IInventory inv = ((IPlayerAccessories) player).getAccessoryInventory();
//        ((IPlayerAccessories) player).updateAccessoriesFromInventory(inv);
//    }
}
