package net.fabricmc.accessories.mixin;

import api.entity.mob.behavior.MultiTemptBehavior;
import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityAIBase;
import net.minecraft.src.EntityAgeable;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiTemptBehavior.class)
public abstract class MultiTemptBehaviorMixin extends EntityAIBase {
    @Shadow protected EntityAgeable myAnimal;

    @Shadow private EntityPlayer temptingPlayer;

    @ModifyArg(method = "shouldExecute", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/World;getClosestPlayerToEntity(Lnet/minecraft/src/Entity;D)Lnet/minecraft/src/EntityPlayer;"), index = 1)
    private double increaseFollowDistance(double par2){
        if(this.temptingPlayer != null) {
            if (ACUtils.hasAccessory(this.temptingPlayer, ACItems.tastyTreat)) {
                return par2 * 1.5d;
            }
        }
        return par2;
    }

    @Inject(method = "shouldExecute", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityPlayer;getCurrentEquippedItem()Lnet/minecraft/src/ItemStack;"), cancellable = true)
    private void followAccessoryPlayer(CallbackInfoReturnable<Boolean> cir){
        if(ACUtils.hasAccessory(this.temptingPlayer,ACItems.tastyTreat)){
            cir.setReturnValue(true);
        }
    }
}
