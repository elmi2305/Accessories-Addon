package net.fabricmc.accessories.mixin;

import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityWither.class)
public class EntityWitherMixin extends EntityMob {
    public EntityWitherMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "dropFewItems", at = @At("TAIL"))
    private void dropWitherRewards(boolean par1, int par2, CallbackInfo ci){
        if (this.worldObj.isRemote) {
            return;
        }

        this.entityDropItem(new ItemStack(ACItems.witherFruit), 0.0f);
        int dust = (int) ((this.rand.nextInt(10) + 6) * (par2 + 1) * 0.85f);
        for (int iTempCount = 0; iTempCount < dust; ++iTempCount) {
            this.dropItem(ACItems.witherDust.itemID, 1);
        }
        if(this.rand.nextInt(Math.max(8 - par2, 2)) == 0){
            this.entityDropItem(new ItemStack(Item.skull.itemID, 1, 1), 0.0f);
        }
    }
}
