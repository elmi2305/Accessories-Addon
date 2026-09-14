package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.Minecraft;
import net.minecraft.src.PlayerControllerMP;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerControllerMP.class)
public class PlayerControllerMPMixin {
    @Shadow @Final private Minecraft mc;

    @Inject(method = "getBlockReachDistance", at = @At("RETURN"),cancellable = true)
    private void extendPlayerReachBasedOnAccessories(CallbackInfoReturnable<Float> cir){
        if(ACUtils.hasAnyAccessory(this.mc.thePlayer, ACItems.extendoGrip)){
            cir.setReturnValue(cir.getReturnValue() + 1.0f);
        }
        else if(ACUtils.hasAnyAccessory(this.mc.thePlayer, ACItems.handStick)){
            cir.setReturnValue(cir.getReturnValue() + 0.5f);
        }
    }
}
