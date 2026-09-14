package net.fabricmc.accessories.mixin;

import api.item.items.ProgressiveCraftingItem;
import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProgressiveCraftingItem.class)
public class ProgressiveCraftingItemMixin {
    @Unique
    private EntityPlayer user;

    @Inject(method = "updateUsingItem", at = @At("HEAD"))
    private void declarePlayer(ItemStack stack, World world, EntityPlayer player, CallbackInfo ci){
        this.user = player;
    }

    @ModifyConstant(method = "updateUsingItem", constant = @Constant(intValue = 4))
    private int increaseKnittingSpeed(int constant){
        if(ACUtils.hasAccessory(this.user, ACItems.sewingKit)){
            return 2;
        }
        return constant;
    }
}
