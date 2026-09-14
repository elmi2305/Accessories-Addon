package net.fabricmc.accessories;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;

import java.util.List;

public class EntityAIAvoidPlayerWithAccessory extends EntityAIBase {
    private final EntityCreature creature;
    private final float avoidDistance;
    private final double farSpeed;
    private final double nearSpeed;
    private final PathNavigate navigator;

    private EntityPlayer targetPlayer;
    private PathEntity pathToAvoid;

    public EntityAIAvoidPlayerWithAccessory(EntityCreature creature, float avoidDistance, double farSpeed, double nearSpeed) {
        this.creature = creature;
        this.avoidDistance = avoidDistance;
        this.farSpeed = farSpeed;
        this.nearSpeed = nearSpeed;
        this.navigator = creature.getNavigator();
        this.setMutexBits(1);
    }

    @Override
    public boolean shouldExecute() {
        List players = this.creature.worldObj.playerEntities;

        double closestSq = Double.MAX_VALUE;
        EntityPlayer closestMatch = null;

        for (Object obj : players) {
            EntityPlayer player = (EntityPlayer) obj;

            if (!player.isEntityAlive() || player.capabilities.isCreativeMode) continue;
            if (player.getDistanceSqToEntity(creature) > avoidDistance * avoidDistance) continue;

            if (hasScaryAccessory(player)) {
                double distSq = player.getDistanceSqToEntity(creature);
                if (distSq < closestSq) {
                    closestSq = distSq;
                    closestMatch = player;
                }
            }
        }

        if (closestMatch == null) return false;

        this.targetPlayer = closestMatch;

        Vec3 playerPos = targetPlayer.worldObj.getWorldVec3Pool()
                .getVecFromPool(targetPlayer.posX, targetPlayer.posY, targetPlayer.posZ);

        Vec3 fleePos = RandomPositionGenerator.findRandomTargetBlockAwayFrom(creature, 16, 7, playerPos);
        if (fleePos == null) return false;

        if (targetPlayer.getDistanceSq(fleePos.xCoord, fleePos.yCoord, fleePos.zCoord) < closestSq) return false;

        this.pathToAvoid = this.navigator.getPathToXYZ(fleePos.xCoord, fleePos.yCoord, fleePos.zCoord);
        return this.pathToAvoid != null && this.pathToAvoid.isDestinationSame(fleePos);
    }

    @Override
    public boolean continueExecuting() {
        return !navigator.noPath();
    }

    @Override
    public void startExecuting() {
        this.navigator.setPath(this.pathToAvoid, this.farSpeed);
    }

    @Override
    public void resetTask() {
        this.targetPlayer = null;
    }

    @Override
    public void updateTask() {
        if (this.creature.getDistanceSqToEntity(this.targetPlayer) < 49.0D) {
            this.navigator.setSpeed(this.nearSpeed);
        } else {
            this.navigator.setSpeed(this.farSpeed);
        }
    }

    // Replace this with your actual accessory detection logic
    private boolean hasScaryAccessory(EntityPlayer player) {
        return ACUtils.hasAnyAccessory(player, ACItems.catEars);
    }
}
