package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.BlockPortal;
import net.minecraft.src.EntityItem;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockPortal.class)
public class BlockPortalMixin {
    @Inject(method = "tryToCreatePortal", at = @At("RETURN"))
    private void dropNetherFruit(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValueZ() || world.isRemote) {
            return;
        }

        world.spawnEntityInWorld(new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D,
                new ItemStack(ACItems.netherFruit)));
    }
}
