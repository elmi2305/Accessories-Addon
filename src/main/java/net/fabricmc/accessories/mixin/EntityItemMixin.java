package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityItem.class)
public abstract class EntityItemMixin extends Entity {
    @Shadow public int delayBeforeCanPickup;

    public EntityItemMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "onUpdate", at = @At("TAIL"))
    private void manageIncreasedRange(CallbackInfo ci){
        if (this.delayBeforeCanPickup == 0 && !this.worldObj.isRemote) {
            EntityPlayer player = this.worldObj.getClosestPlayerToEntity(this,3);
            if(player != null && ACUtils.hasAnyAccessory(player, ACItems.lootMagnet)){
                this.setPosition(player.posX,player.posY - player.height / 2 + 0.5,player.posZ);
            }
        }
    }
}
