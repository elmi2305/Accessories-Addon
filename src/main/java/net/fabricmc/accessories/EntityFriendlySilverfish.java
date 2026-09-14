package net.fabricmc.accessories;

import net.minecraft.src.*;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.List;

public class EntityFriendlySilverfish extends EntitySilverfish {
    public EntityFriendlySilverfish(World par1World) {
        super(par1World);
    }

    @Override
    protected Entity findPlayerToAttack() {
        List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                this.boundingBox.expand(10.0D, 4.0D, 10.0D)
        );

        EntityMob closest = null;
        double closestDistSq = Double.MAX_VALUE;

        for (Entity e : entities) {
            if (e instanceof EntityMob && !(e instanceof EntityFriendlySilverfish)) {
                double distSq = this.getDistanceSqToEntity(e);
                if (distSq < closestDistSq) {
                    closestDistSq = distSq;
                    closest = (EntityMob) e;
                }
            }
        }
        if(this.getAttackTarget() instanceof EntityPlayer){
            this.setAttackTarget(closest);
        }
        return closest;
    }

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        if(!this.worldObj.isRemote && par1Entity instanceof EntityLivingBase){
            ((EntityLivingBase) par1Entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
        }
        return super.attackEntityAsMob(par1Entity);
    }
}
