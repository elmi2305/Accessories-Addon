package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.EntityAIAvoidPlayerWithAccessory;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityCreeper.class)
public class EntityCreeperMixin extends EntityMob {
    public EntityCreeperMixin(World par1World) {
        super(par1World);

    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addAITasks(World par1World, CallbackInfo ci){
        this.tasks.addTask(2, new EntityAIAvoidPlayerWithAccessory(this, 9.0F, 1.0D, 1.2D));
    }

}
