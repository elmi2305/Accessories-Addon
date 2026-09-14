package net.fabricmc.accessories.mixin;

import api.item.items.FireStarterItem;
import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(FireStarterItem.class)
public abstract class FireStarterItemMixin {
    @Shadow protected abstract boolean checkChanceOfStart(ItemStack itemStack, Random random);

    @Shadow protected abstract boolean attemptToLightBlock(ItemStack stack, World world, int i, int j, int k, int iFacing);

    @Shadow protected abstract void performUseEffects(EntityPlayer player);

    @Inject(method = "onItemUse", at = @At(value = "HEAD"))
    private void editChanceOfFireStarter(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int iFacing, float fClickX, float fClickY, float fClickZ, CallbackInfoReturnable<Boolean> cir){
        if(ACUtils.hasAccessory(player, ACItems.boxOfMatches)){
            if (player.canPlayerEdit(i, j, k, iFacing, stack)) {
                this.performUseEffects(player);
                if (!world.isRemote) {
                    if (this.checkChanceOfStart(stack, world.rand)) {
                        this.attemptToLightBlock(stack, world, i, j, k, iFacing);
                    }
                }
            }
        }
    }
}
