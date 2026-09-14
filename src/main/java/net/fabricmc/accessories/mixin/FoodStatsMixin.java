package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FoodStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodStats.class)
public class FoodStatsMixin {
    @Unique
    private EntityPlayer player;
    private int ticksStandingStill;
    private double lastPosX;
    private double lastPosZ;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void ensurePlayerDeclaration(EntityPlayer player, CallbackInfo ci) {
        this.player = player;
        this.calculateTicksStandingStillForPlayer();
    }


    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Ljava/lang/Float;floatValue()F"))
    private float customHealthMultiplierBasedOnAccessories(Float instance) {
        float healthRegenMultiplier = 1.0f; // base value

        if (this.player != null) {
            if (ACUtils.hasAnyAccessory(player, ACItems.celestialStone)) {
                healthRegenMultiplier *= 0.7f; // equivalent of -4 modifier
            } else {
                boolean isDaytime = player.worldObj.isDaytime();
                if (ACUtils.hasAnyAccessory(player, ACItems.sunStone)) {
                    healthRegenMultiplier *= (isDaytime ? 0.6f : 1.2f); // -4 or +2
                }
                if (ACUtils.hasAnyAccessory(player, ACItems.moonStone)) {
                    healthRegenMultiplier *= (isDaytime ? 1.2f : 0.6f); // +2 or -4
                }
                // 0% -> 40% increased regen
            }

            if (ACUtils.hasAnyAccessory(player, ACItems.eyeOfTheSun)) {
                // Every 30 ticks standing still makes healing slower
                int stillnessFactor = this.ticksStandingStill / 30;
                healthRegenMultiplier *= (1.0f - (stillnessFactor * 0.15f));
                // 0% -> 75% increased regen
            }

            healthRegenMultiplier = Math.max(0.1f, Math.min(healthRegenMultiplier, 5.0f));
        }


        return instance * healthRegenMultiplier;
    }











    // CLEANLINESS


    @Unique private void calculateTicksStandingStillForPlayer(){
        boolean wasStandingStill = true;
        if(this.lastPosX != this.player.posX){
            this.lastPosX = this.player.posX;
            wasStandingStill = false;
        }
        if(this.lastPosZ != this.player.posZ){
            this.lastPosZ = this.player.posZ;
            wasStandingStill = false;
        }
        if(wasStandingStill){
            this.ticksStandingStill = Math.min(this.ticksStandingStill + 1, 150);
        } else{
            this.ticksStandingStill = 0;
        }
    }
}
