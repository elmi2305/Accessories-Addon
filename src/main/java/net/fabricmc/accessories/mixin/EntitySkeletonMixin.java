package net.fabricmc.accessories.mixin;

import api.entity.component.VariantComponent;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonMixin extends EntityMob {
    @Shadow public abstract VariantComponent.EntityVariant getSkeletonType();

    public EntitySkeletonMixin(World par1World) {
        super(par1World);
    }


    @Inject(method = "dropFewItems", at = @At("TAIL"))
    private void dropWitherDust(boolean bKilledByPlayer, int iLootingModifier, CallbackInfo ci){
        if (this.getSkeletonType().id() == 1) {
            int dust = this.rand.nextInt(5 + iLootingModifier);
            for (int iTempCount = 0; iTempCount < dust; ++iTempCount) {
                this.dropItem(ACItems.witherDust.itemID, 1);
            }
        }
    }
}
