package net.fabricmc.accessories.mixin;

import net.minecraft.src.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("onGround")
    boolean getIsGrounded();
}
