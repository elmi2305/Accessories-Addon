package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import btw.entity.mob.BTWSquidEntity;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BTWSquidEntity.class)
public class BTWSquidEntityMixin extends EntityWaterMob {
    public BTWSquidEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void removeSquidTentacleIfAccessory(CallbackInfo ci) {
        if (this.entityToAttack instanceof EntityPlayer player) {
            if (ACUtils.hasAnyAccessory(player, ACItems.pendantSea, ACItems.pendantTides)) {
                this.entityToAttack = null;
            }
        }
    }
    @Inject(method = "setTarget", at = @At("HEAD"),cancellable = true)
    private void preventSquidFromAttackingPlayerAccessory(Entity targetEntity, CallbackInfo ci){
        if(targetEntity instanceof EntityPlayer player && ACUtils.hasAnyAccessory(player,ACItems.pendantSea)){
            this.entityToAttack = null;
            ci.cancel();
        }
    }

}
