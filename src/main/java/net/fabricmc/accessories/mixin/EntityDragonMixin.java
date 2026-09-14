package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.EntityDragon;
import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityDragon.class)
public abstract class EntityDragonMixin extends EntityLivingBase {
    protected EntityDragonMixin(World world) {
        super(world);
    }

    @Inject(method = "createEnderPortal", at = @At("HEAD"))
    private void dropDragonHeart(int x, int z, CallbackInfo ci) {
        this.entityDropItem(new ItemStack(ACItems.dragonHeart), 0.0F);
    }
}
