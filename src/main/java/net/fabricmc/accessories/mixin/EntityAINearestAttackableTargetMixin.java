package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityAINearestAttackableTarget.class)
public abstract class EntityAINearestAttackableTargetMixin extends EntityAITarget {
    @Shadow private EntityLivingBase targetEntity;

    public EntityAINearestAttackableTargetMixin(EntityCreature par1EntityCreature, boolean par2) {
        super(par1EntityCreature, par2);
    }

    @Inject(method = "getTargetDistance", at = @At("TAIL"), cancellable = true)
    private void manageAccessoryMobAggroRadius(CallbackInfoReturnable<Double> cir){
        if(this.targetEntity instanceof EntityPlayer){
            double aggroMult = 1d;
            if(ACUtils.hasAnyAccessory((EntityPlayer) this.targetEntity, ACItems.rottenPerfume)){
                aggroMult = 0.75;
                if(this.taskOwner instanceof EntityZombie || this.taskOwner instanceof EntitySkeleton){
                    aggroMult = 0.6;
                }
            } else
            if(ACUtils.hasAnyAccessory((EntityPlayer) this.targetEntity, ACItems.putridScent)){
                aggroMult = 0.85;
            }
            if(ACUtils.hasAccessory((EntityPlayer) this.targetEntity, ACItems.monsterNecklace)){
                if(aggroMult < 1){
                    aggroMult += 0.25; // adds instead of multiplies so that it can undo previous accessories
                } else{
                    aggroMult *= 1.25;
                }
            }

            cir.setReturnValue(cir.getReturnValue() * aggroMult);
        }
    }


    @Inject(method = "shouldExecute", at = @At("TAIL"), cancellable = true)
    private void manageFriendlyCreepers(CallbackInfoReturnable<Boolean> cir){
        if(this.taskOwner instanceof EntityCreeper && this.targetEntity instanceof EntityPlayer player){
            if(ACUtils.hasAccessory(player, ACItems.catEars) && ACUtils.hasAccessory(player, ACItems.skirt)){
                cir.setReturnValue(false);
            }
        }
    }
}
