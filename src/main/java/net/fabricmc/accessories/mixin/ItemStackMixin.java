package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.items.ItemSoulSword;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Inject(method = "attemptDamageItem", at = @At("HEAD"), cancellable = true)
    private void preventSoulSwordDamage(int amount, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (((ItemStack)(Object)this).getItem() instanceof ItemSoulSword) {
            cir.setReturnValue(false);
        }
    }
}
