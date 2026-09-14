package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import net.fabricmc.accessories.EntityFriendlySilverfish;
import net.fabricmc.accessories.items.ACItems;
import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity implements EntityAccessor{
    @Unique private int playerJumpCount = 0;
    private boolean isDashing;

    public EntityLivingBaseMixin(World par1World) {
        super(par1World);
    }


    @Shadow public abstract void jump();

    @Shadow private int jumpTicks;

    @Shadow public float jumpMovementFactor;

    @Shadow protected abstract float getSwimmingHorizontalModifier();

    @Shadow public abstract boolean attackEntityFrom(DamageSource par1DamageSource, float par2);

    @Shadow protected EntityPlayer attackingPlayer;

    @Shadow private EntityLivingBase lastAttacker;

    @Shadow private int lastAttackerTime;

    @Shadow public float rotationYawHead;

    @Shadow public float cameraPitch;

    @Inject(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;canJump()Z"))
    private void manageExtraPlayerJumps(CallbackInfo ci) {
        EntityLivingBase thisObj = (EntityLivingBase)(Object)this;

        if (thisObj instanceof EntityPlayer player) {

            // Count how many accessories provide extra jumps
            int maxExtraJumps = 0;
            if(ACUtils.hasAnyAccessory(player, ACItems.bundleBalloons, ACItems.bundleHorseshoeBalloons)) {
                maxExtraJumps = 3;
            }
            else if (ACUtils.hasAnyAccessory(player, ACItems.cloudInABottle, ACItems.cloudBalloon, ACItems.spectreBoots)) {
                maxExtraJumps++;
            }


            // Only trigger extra jump if conditions are right
            if (maxExtraJumps > 0 && this.playerJumpCount < maxExtraJumps && this.jumpTicks < 5) {
//                System.out.println("Extra jump " + (this.playerJumpCount + 1));
                this.playerJumpCount++;
                this.jump();
                this.jumpTicks = 10;
            }
        }
        if (this.getIsGrounded() && this.playerJumpCount != 0) {
            this.playerJumpCount = 0;
//            System.out.println("Reset jump count");
        }
    }

    @ModifyArgs(method = "moveEntityWithHeading", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;moveFlying(FFF)V"))
    private void boostAirAcceleration(Args args){
        EntityLivingBase thisObj = (EntityLivingBase)(Object)this;
        if (!this.getIsGrounded() && thisObj instanceof EntityPlayer) {
            if (ACUtils.hasAnyAccessory((EntityPlayer) thisObj, ACItems.bundleBalloons, ACItems.bundleHorseshoeBalloons)) {
                float x = args.get(2);
                args.set(2, x * 2f);
            }
        }
    }
    @Redirect(method = "moveEntityWithHeading", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;getSwimmingHorizontalModifier()F"))
    private float increaseSwimmingAcceleration(EntityLivingBase instance){
        if(instance instanceof EntityPlayer obj){
            if(ACUtils.hasAccessory(obj,ACItems.pendantSea)){
                return this.getSwimmingHorizontalModifier() * 1.2f;
            }
        }
        return this.getSwimmingHorizontalModifier();
    }

    @Unique private int lightningBoundTicks = -1;
    @Unique private int explosionBoundTicks = -1;
    @Unique private int crumblingBoundTicks = -1;



    @Unique private float savedYaw = -1;
    @Unique private float savedPitch = -1;

    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void tickLightningBound(CallbackInfo ci) {
        EntityLivingBase self = (EntityLivingBase) (Object) this;
        if(self instanceof EntityPlayer) return;
        int lightningBoundTicks1 = this.lightningBoundTicks;

        if (lightningBoundTicks1 > 0) {
            this.setLightningBound(lightningBoundTicks1 - 1);

            if (!self.worldObj.isRemote) {
                // Strike when timer ends
                if (lightningBoundTicks1 - 1 == 0) {
                    self.worldObj.addWeatherEffect(new EntityLightningBolt(
                            self.worldObj, self.posX, self.posY, self.posZ
                    ));
                }
            } else {
                // Client shows particles only
                this.spawnLightningParticles(self);
            }
        }



        int explosionBoundTicks = this.explosionBoundTicks;

        if (explosionBoundTicks > 0) {
            this.setExplosionBound(explosionBoundTicks - 1);

            if (!self.worldObj.isRemote) {
                // Strike when timer ends
                if (explosionBoundTicks - 1 == 0) {
                    this.attackEntityFrom(DamageSource.generic, 8f);
                    this.worldObj.createExplosion(this.lastAttacker, this.posX,this.posY+0.5, this.posZ, 2.0f, this.hasFireAspect(this.lastAttacker));
                }
            } else {
                // Client shows particles only
                this.spawnExplosionParticles(self);
            }
        }



        int crumblingBoundTicks = this.crumblingBoundTicks;

        if (crumblingBoundTicks > 0) {
            this.setCrumblingBound(crumblingBoundTicks - 1);

            if (!self.worldObj.isRemote) {
                // Strike when timer ends
                if (crumblingBoundTicks - 1 == 0) {
                    if(this.isDashing){
                        this.isDashing = false;
                    }
                }
            } else {
                // Client shows particles only
                this.spawnCrumblingParticles(self);
            }
        }
    }

    @Override
    public void moveEntity(double dMoveX, double dMoveY, double dMoveZ) {
        if(this.crumblingBoundTicks > 0) return;
        super.moveEntity(dMoveX, dMoveY, dMoveZ);
    }

    @Override
    public void moveFlying(float par1, float par2, float par3) {
        if(this.crumblingBoundTicks > 0) return;
        super.moveFlying(par1, par2, par3);
    }



    @Inject(method = "attackEntityFrom", at = @At("HEAD"))
    private void onAttacked(DamageSource source, float par2, CallbackInfoReturnable<Boolean> cir) {

        // runs both client and server
        if (!source.isProjectile() && source.getEntity() instanceof EntityPlayer attacker) {
            ItemStack acc = ACUtils.findAccessory(attacker, s -> s.getItem() == ACItems.lightningEnchantment);
            if (acc != null && acc.getItem() instanceof AccessoryItem item) {
                if (ACUtils.getActiveCooldown(attacker,item) <= 0)  {
                    this.setLightningBound(40);
                    if (!this.worldObj.isRemote) {
                        item.setCooldown(item.getFinalCooldown());
                    }
                }
            }

            acc = ACUtils.findAccessory(attacker, s -> s.getItem() == ACItems.explosionEnchantment);
            if (acc != null && acc.getItem() instanceof AccessoryItem item) {
                if (ACUtils.getActiveCooldown(attacker,item) <= 0)  {
                    this.setExplosionBound(60);
                    if (!this.worldObj.isRemote) {
                        item.setCooldown(item.getFinalCooldown());
                    }
                }
            }

            acc = ACUtils.findAccessory(attacker, s -> s.getItem() == ACItems.crumblingEnchantment);
            if (acc != null && acc.getItem() instanceof AccessoryItem item) {
                if (ACUtils.getActiveCooldown(attacker,item) <= 0)  {
                    this.setCrumblingBound(80);
                    this.savedPitch = this.rotationPitch;
                    this.savedYaw = this.rotationYaw;
                    if (!this.worldObj.isRemote) {
                        item.setCooldown(item.getFinalCooldown());
                    }
                }
            }
            acc = ACUtils.findAccessory(attacker, s -> s.getItem() == ACItems.velocityEnchantment);
            if (acc != null && acc.getItem() instanceof AccessoryItem item) {
                if (ACUtils.getActiveCooldown(attacker, item) <= 0 && !this.isDashing) {
                    this.setCrumblingBound(20);
                    attacker.hurtResistantTime = 20;

                    if (this.startSwordDash(attacker, this) && !this.worldObj.isRemote) {
                        item.setCooldown(item.getFinalCooldown());
                    }
                }
            }
        }
    }

    @ModifyArgs(method = "attackEntityFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;damageEntity(Lnet/minecraft/src/DamageSource;F)V"))
    private void damageTaken(Args args){
        DamageSource source = args.get(0);
        float par1 = args.get(1);

        if(this.crumblingBoundTicks > 0){
            args.set(1, par1 * 1.15f);
        }
    }
    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void manageMobsIndirectly(CallbackInfo ci){
        EntityLivingBase obj = (EntityLivingBase) (Object)this;

        if(obj instanceof EntityLiving obj1 && this.crumblingBoundTicks > 0){
            if(obj1.hasAttackTarget()){
                obj1.setAttackTarget(null);
            }
            obj1.setPositionAndRotation(this.posX,this.posY,this.posZ, this.savedYaw, this.savedPitch);
            obj1.setPositionAndUpdate(this.posX,this.posY,this.posZ);

            if(obj1.rotationYawHead != this.savedYaw){
                obj1.setRotationYawHead(this.savedYaw);
            }
            if(obj1.hurtResistantTime > 16){
                obj1.hurtResistantTime = 16;
            }
        }
    }


    @Inject(method = "onDeathUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;getExperiencePoints(Lnet/minecraft/src/EntityPlayer;)I", ordinal = 0))
    private void spawnSilverfishOnDeath(CallbackInfo ci){
        EntityLivingBase obj = (EntityLivingBase) (Object)this;
        if(!(obj instanceof EntityMob)) return;
        if(obj instanceof EntityFriendlySilverfish) return;
        if(ACUtils.hasAccessory(this.attackingPlayer, ACItems.silverfishEnchantment) && ACUtils.getActiveCooldown(this.attackingPlayer,ACItems.silverfishEnchantment) == 0){
            double radius = 0.75; // distance from the center
            for (int i = 0; i < 3; i++) {
                double angle = (2 * Math.PI / 3) * i; // 120° spacing
                double offsetX = Math.cos(angle) * radius;
                double offsetZ = Math.sin(angle) * radius;

                EntityFriendlySilverfish silverfish = new EntityFriendlySilverfish(worldObj);
                silverfish.setLocationAndAngles(this.posX + offsetX, this.posY, this.posZ + offsetZ, 0.0F, 0.0F);
                worldObj.spawnEntityInWorld(silverfish);
                ACUtils.resetAccessoryCooldown(this.attackingPlayer, ACItems.silverfishEnchantment);
            }
        }
    }













    // HELPERS
    @Unique private void spawnLightningParticles(EntityLivingBase self){
        for (int i = 0; i < 4; i++) {
            self.worldObj.spawnParticle("crit",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("smoke",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("portal",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height - 0.25,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("portal",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height - 0.25,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
        }
    }
    @Unique private void spawnExplosionParticles(EntityLivingBase self){
        for (int i = 0; i < 4; i++) {
            self.worldObj.spawnParticle("crit",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("fire",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("largesmoke",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height - 0.25,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("fire",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height - 0.25,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
        }
    }
    @Unique private void spawnCrumblingParticles(EntityLivingBase self){
        for (int i = 0; i < 4; i++) {
            self.worldObj.spawnParticle("crit",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("crit",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
            self.worldObj.spawnParticle("crit",
                    self.posX + (self.rand.nextDouble() - 0.5) * self.width,
                    self.posY + self.rand.nextDouble() * self.height - 0.25,
                    self.posZ + (self.rand.nextDouble() - 0.5) * self.width,
                    0, 0, 0);
        }
    }


    @Unique
    private boolean hasFireAspect(Entity player) {
        if (player == null) return false;
        if (!(player instanceof EntityPlayer p)) return false;
        ItemStack held = p.getHeldItem();
        if (held == null) return false;
        return EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, held) > 0;
    }



    @Unique
    public void setLightningBound(int ticks) {
        this.lightningBoundTicks = ticks;
    }
    @Unique
    public void setExplosionBound(int ticks) {
        this.explosionBoundTicks = ticks;
    }
    @Unique
    public void setCrumblingBound(int ticks) {
        this.crumblingBoundTicks = ticks;
    }




    private boolean startSwordDash(EntityPlayer attacker, Entity target) {
        // Make sure the player is holding a sword
        ItemStack held = attacker.getHeldItem();
        if (held == null || !(held.getItem() instanceof ItemSword)) {
            return false;
        }

        // Direction vector from attacker to target
        double dx = target.posX - attacker.posX;
        double dz = target.posZ - attacker.posZ;
        double dist = Math.min(Math.sqrt(Math.max(dx * dx + dz * dz, 1)), 5);

        // Normalize and apply dash speed
        double dashSpeed = 1.5D; // Adjust as needed for desired dash distance
        attacker.motionX = (dx / dist) * dashSpeed;
        attacker.motionZ = (dz / dist) * dashSpeed;
        attacker.motionY = 0.4D; // Small jump effect

        // Mark player for update (client-side movement sync)
        attacker.velocityChanged = true;
        this.isDashing = true;

        // Damage target with the sword's damage value
        if (!attacker.worldObj.isRemote) {
            float damage = (float)attacker.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
            target.attackEntityFrom(DamageSource.causePlayerDamage(attacker), damage);
        }
        return true;
    }


}
