package net.fabricmc.accessories.mixin;

import btw.community.accessories.ACUtils;
import btw.community.accessories.AccessoriesProgressData;
import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.ISoulSwordCombatState;
import net.fabricmc.accessories.ICombatAccessoryTarget;
import net.fabricmc.accessories.items.ACItems;
import net.fabricmc.accessories.items.AccessoryItem;
import net.fabricmc.accessories.items.ItemSoulSword;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.Arrays;
import java.util.List;

@Mixin(value = EntityPlayer.class, priority = 1100)
public abstract class EntityPlayerMixin extends EntityLivingBase implements IPlayerAccessories, ISoulSwordCombatState {
    private float prevTickYaw;
    private float prevTickPitch;

    private Entity lastHitEntity;
    @Unique private boolean soulSwordRepairAllowed;

    @Shadow public abstract boolean attackEntityFrom(DamageSource par1DamageSource, float par2);


    @Shadow public abstract World getEntityWorld();

    @Shadow public abstract boolean isBlocking();

    @Shadow public abstract ItemStack getHeldItem();

    @Shadow public Container openContainer;
    private final ItemStack[] accessories = new ItemStack[AccessoriesProgressData.MAX_ACCESSORY_SLOTS];
    private final InventoryBasic accessoryInventoryBacking = new InventoryBasic("Accessories", false, accessories.length);

    public EntityPlayerMixin(World par1World) {
        super(par1World);
    }

    @Override
    public ItemStack[] getAccessories() {
        syncAccessoriesFromInventory();
        return Arrays.copyOf(this.accessories, AccessoriesProgressData.getAccessorySlotCount((EntityPlayer) (Object) this));
    }

    @Override
    public void setAccessories(ItemStack[] accessories) {
        for (int i = 0; i < this.accessories.length; i++) {
            this.accessories[i] = (i < accessories.length) ? accessories[i] : null;
            this.accessoryInventoryBacking.setInventorySlotContents(i, this.accessories[i]); // Sync
        }
    }

    @Override
    public InventoryBasic getAccessoryInventoryBacking() {
        return this.accessoryInventoryBacking;
    }

    @Override
    public void setAccessoryInventoryBacking(InventoryBasic inv) {
        // Probably not needed anymore
    }

    @Inject(
            method = "writeEntityToNBT",
            at = @At("HEAD")      // ← was HEAD
    )
    private void writeAccessoriesToNBT(NBTTagCompound tag, CallbackInfo ci) {
        syncAccessoriesFromInventory();
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < accessories.length; i++) {
            if (accessories[i] != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte) i);
                accessories[i].writeToNBT(itemTag);
                list.appendTag(itemTag);
            }
        }
        tag.setTag("Accessories", list);

//        System.out.println("Writing accessories to NBT! Contents:");
//        for (int i = 0; i < accessories.length; i++) {
//            System.out.println("Slot " + i + ": " + accessories[i]);
//        }
    }

    @Unique
    private void syncAccessoriesFromInventory() {
        for (int i = 0; i < this.accessories.length; i++) {
            this.accessories[i] = this.accessoryInventoryBacking.getStackInSlot(i);
        }
    }

    @Inject(
            method = "readEntityFromNBT",
            at = @At("HEAD")
    )
    private void readAccessoriesFromNBT(NBTTagCompound tag, CallbackInfo ci) {
        // clear before reading
        Arrays.fill(accessories, null);

        NBTTagList list = tag.getTagList("Accessories");
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound itemTag = (NBTTagCompound) list.tagAt(i);
            int slot = itemTag.getByte("Slot") & 0xFF;
            if (slot >= 0 && slot < accessories.length) {
                accessories[slot] = ItemStack.loadItemStackFromNBT(itemTag);
            }
        }

        for (int i = 0; i < accessories.length; i++) {
            accessoryInventoryBacking.setInventorySlotContents(i, accessories[i]);
        }
    }








    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING
    // BEGIN CUSTOM ACCESSORY HANDLING







    @ModifyVariable(method = "attackEntityFrom", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private float lowerTakenDamageBasedOnAccessory(float trueDamage, DamageSource source){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(source == DamageSource.fall){
            float fFallDamageReduction = 1f;

            if(ACUtils.hasAnyAccessory(obj,ACItems.shinyBalloon,ACItems.cloudBalloon, ACItems.bundleBalloons)){
                fFallDamageReduction *= 0.75f;
            }
            if(ACUtils.hasAccessory(obj, ACItems.spectreBoots)){
                fFallDamageReduction *= 0.7f;
            }
            return trueDamage * fFallDamageReduction;
        }
        if(ACUtils.hasAnyAccessory(obj, ACItems.steelShield) && (source.isExplosion() || source.isProjectile())){
            trueDamage *= 0.9f;
        }
        if(ACUtils.hasAnyAccessory(obj,ACItems.celestialStone) && !this.worldObj.isDaytime()){
            trueDamage *= 0.94f;
        }
        return trueDamage;
    }
    @Inject(method = "attackEntityFrom", at = @At("HEAD"), cancellable = true)
    private void cancelFallDamageBasedOnAccessories(DamageSource source, float par2, CallbackInfoReturnable<Boolean> cir){
        EntityPlayer obj = ((EntityPlayer) (Object) this);

        if(source == DamageSource.fall) {
            if(ACUtils.hasAnyAccessory(obj, ACItems.luckyHorseshoe, ACItems.bundleHorseshoeBalloons)){
                cir.setReturnValue(false);
                return;
            }
        }
        if(ACUtils.hasAnyAccessory(obj, ACItems.counterScarf) && this.rand.nextInt(10) == 0){
            cir.setReturnValue(false);
            return;
        }
        if(ACUtils.hasAccessory(obj,ACItems.lightningEnchantment)){
            if(source.getSourceOfDamage() instanceof EntityLightningBolt ){
                cir.setReturnValue(false);
                return;
            }
        }
        if(ACUtils.isAccessoryReady(obj, ACItems.katanaEnchantment) && isBlockingWithSword(obj)) {
            if (source.getSourceOfDamage() instanceof EntityLivingBase attacker) {
                this.doKatanaDamage(attacker,obj,par2);
                cir.setReturnValue(false);
            } else if (source.getSourceOfDamage() instanceof EntityArrow arrow && arrow.shootingEntity instanceof EntityLivingBase attacker) {
                if (this == attacker) return;
                EntityArrow reflectedArrow = new EntityArrow(this.worldObj);
                reflectedArrow.copyLocationAndAnglesFrom(arrow);
                reflectedArrow.motionX = -arrow.motionX / 1.5;
                reflectedArrow.motionY = -arrow.motionY;
                reflectedArrow.motionZ = -arrow.motionZ / 1.5;
                reflectedArrow.shootingEntity = this;
                arrow.setDead();

                this.worldObj.spawnEntityInWorld(reflectedArrow);
                this.swingItem();
                this.getHeldItem().attemptDamageItem(6 + this.rand.nextInt(6), this.rand);
                ACUtils.resetAccessoryCooldown(obj, ACItems.katanaEnchantment);
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "attackEntityFrom", at = @At("HEAD"))
    private void applyCombatAccessoriesAgainstPlayer(DamageSource source, float damage, CallbackInfoReturnable<Boolean> cir) {
        ((ICombatAccessoryTarget) (Object) this).accessories$applyCombatAccessoryEffects(source);
    }
    @Unique
    private void doKatanaDamage(EntityLivingBase attacker, EntityPlayer obj, float par2){
        attacker.attackEntityFrom(DamageSource.causePlayerDamage(obj), par2 * 1.5f);
        this.swingItem();
        this.getHeldItem().attemptDamageItem(6 + this.rand.nextInt(6), this.rand);
        ACUtils.resetAccessoryCooldown(obj, ACItems.katanaEnchantment);
    }


    @Redirect(method = "moveEntityWithHeading", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/PlayerCapabilities;getFlySpeed()F"))
    private float setJumpMovementFactorBasedOnAccessoriesWorn(PlayerCapabilities instance){
        EntityPlayer obj = ((EntityPlayer) (Object)this);
        if(ACUtils.hasAnyAccessory(obj, ACItems.bundleBalloons, ACItems.bundleHorseshoeBalloons)){
            return instance.getFlySpeed() * 2f; // creative mode fly speed
        }
        return instance.getFlySpeed();
    }

    @ModifyArgs(method = "moveEntityWithHeading", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/EntityLivingBase;moveEntityWithHeading(FF)V"))
    private void setJumpMovementFactorBasedOnAccessoriesSurvival(Args args){
        float x = args.get(0), z = args.get(1);
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if (ACUtils.hasAnyAccessory(obj,ACItems.bundleBalloons, ACItems.bundleHorseshoeBalloons)) {
            args.set(0, x * 2.15f);
            args.set(1, z * 2.15f);
        }
    }

    @Inject(method = "jump", at = @At("TAIL"))
    private void increaseHeightOfJumpBasedOnAccessory(CallbackInfo ci){
        EntityPlayer obj = ((EntityPlayer) (Object)this);
        if(ACUtils.hasAnyAccessory(obj,ACItems.frogLeg)){
            this.motionY *= 1.2f;
        } else if(ACUtils.hasAccessory(obj,ACItems.springBoots)){
            this.motionY *= 1.4f;
        }
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Entity;attackEntityFrom(Lnet/minecraft/src/DamageSource;F)Z"))
    private void storeEnemyHit(Entity par1Entity, CallbackInfo ci){
        this.lastHitEntity = par1Entity;
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
    private void recordSoulSwordRepairEligibility(Entity target, CallbackInfo ci) {
        ItemStack heldItem = this.getHeldItem();
        this.soulSwordRepairAllowed = heldItem != null && heldItem.getItem() instanceof ItemSoulSword
                && (!(target instanceof EntityLivingBase) || ((EntityLivingBase)target).hurtResistantTime == 0);
    }

    @Override
    public boolean accessories$canRepairSoulSword() {
        return this.soulSwordRepairAllowed;
    }
    @ModifyArg(method = "attackTargetEntityWithCurrentItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/src/Entity;attackEntityFrom(Lnet/minecraft/src/DamageSource;F)Z"), index = 1)
    private float increaseGivenDamageByPlayer(float par2){
        EntityPlayer obj = ((EntityPlayer) (Object)this);
        ItemStack heldItem = obj.getHeldItem();
        if (heldItem != null && heldItem.getItem() instanceof ItemSoulSword) {
            ItemSoulSword soulSword = (ItemSoulSword)heldItem.getItem();
            par2 += soulSword.getAttackDamage(heldItem) - ItemSoulSword.BASE_ATTACK_DAMAGE;
        }
        float damageMult = 1.0f;
        int offset = 0;
        Entity lastHitEntity = this.lastHitEntity;
        if(lastHitEntity == null) return par2;


        if(ACUtils.hasAnyAccessory(obj,ACItems.celestialStone)){
            damageMult *= 1.10f;
        }
        if(ACUtils.hasAccessory(obj,ACItems.gamblersDelight)){
            damageMult *= this.rand.nextFloat(0.5f, 1.5f);
        }
        if(ACUtils.hasAccessory(obj,ACItems.gamblersDream)){
            damageMult *= (this.rand.nextBoolean() ? 2 : 0);
        }
        if(ACUtils.hasAccessory(obj,ACItems.gamblersParadise)){
            boolean bChosenOutcome = this.rand.nextBoolean();
            damageMult *= (bChosenOutcome ? 3 : 0);
            if(!bChosenOutcome){
                this.attackEntityFrom(DamageSource.causePlayerDamage(obj), par2);
            }
        }
        if(ACUtils.hasAccessory(obj, ACItems.theDice) && lastHitEntity instanceof EntityLivingBase){
            if(this.rand.nextInt(10) == 0){
                damageMult = 5000;
                this.attackEntityFrom(DamageSource.causePlayerDamage(obj), ((EntityLivingBase) lastHitEntity).getMaxHealth());
            } else{
                damageMult = 0;
            }
        }
        if(ACUtils.hasAccessory(obj,ACItems.momentumBlade)){
            damageMult *= this.getDamageMultiplier(this.rotationYaw,this.prevTickYaw,this.rotationPitch,this.prevTickPitch);
        }
        if(ACUtils.hasAccessory(obj,ACItems.vampireEnchantment)) {
            if (this.rand.nextInt((int) Math.floor(this.getHealth()) + 1) < 4) {
                int health = this.rand.nextInt(2) + 1;
                this.heal(health);
                offset = (int) Math.max((health * -1.75f), -par2 + 1);
            }
        }
        if (ACUtils.hasAccessory(obj, ACItems.sweepingEdge)) {
            World world = obj.worldObj;

            double sweepRange = 2D;       // How far the sweep reaches
            double sweepAngle = 90.0D;      // Total angle in degrees (centered on player look)
            float baseDamage = 3.0F;        // Base damage for the sweep

            // Get nearby entities
            List nearby = world.getEntitiesWithinAABBExcludingEntity(obj, obj.boundingBox.expand(sweepRange, 1.5D, sweepRange));
            for (Object o : nearby) {
                if (!(o instanceof EntityLivingBase target)) continue;
                if (target == obj) continue;
                if (target.hurtResistantTime > (target.maxHurtResistantTime / 2)) continue;

                // Vector from player to target
                double dx = target.posX - obj.posX;
                double dz = target.posZ - obj.posZ;

                // Player look direction
                float yawRad = (float) Math.toRadians(obj.rotationYaw);
                double lookX = -Math.sin(yawRad);
                double lookZ =  Math.cos(yawRad);

                // Normalize vector to target
                double distXZ = Math.sqrt(dx*dx + dz*dz);
                if (distXZ == 0) continue;
                double dirX = dx / distXZ;
                double dirZ = dz / distXZ;

                // Angle between player look and target
                double dot = lookX*dirX + lookZ*dirZ;
                double angle = Math.toDegrees(Math.acos(dot));

                if (angle <= sweepAngle / 2) {
                    // Scale damage based on angle (closer to center = more damage)
                    float damage = baseDamage * (float)(1.0 - (angle / (sweepAngle/2) * 0.5)); // at edge, 50% damage
                    if (!world.isRemote) {
                        target.attackEntityFrom(DamageSource.generic, damage);
                        if (target instanceof EntityLivingBase) {
                            target.knockBack(target, 0.5F, -dx, -dz);
                        }
                    }
                }
            }
        }




        return (par2 + offset) * damageMult;
    }




    @Override
    public float getAIMoveSpeed() {
        float fSpeedModifier = 1f;
        EntityPlayer obj = ((EntityPlayer) (Object)this);
        if(ACUtils.hasAnyAccessory(obj,ACItems.spectreBoots)){
            fSpeedModifier *= 1.2f;
        } else
        if(ACUtils.hasAnyAccessory(obj,ACItems.hermesBoots)){
            fSpeedModifier *= 1.1f;
        }
        return super.getAIMoveSpeed() * fSpeedModifier;
    }


    @Override
    public float knockbackMagnitude() {
        float dKnockbackAccessoryModifier = 1.0f;
        EntityPlayer obj = ((EntityPlayer) (Object)this);


        if(ACUtils.hasAnyAccessory(obj,ACItems.obsidianShield)){
            dKnockbackAccessoryModifier *= 0.85f;
        }
        if(ACUtils.hasAnyAccessory(obj,ACItems.steelShield)){
            dKnockbackAccessoryModifier *= 0.7f;
        }


        return super.knockbackMagnitude() * dKnockbackAccessoryModifier;
    }


    @ModifyConstant(method = "getCurrentPlayerStrVsBlock", constant = @Constant(floatValue = 5.0f))
    private float increaseWaterAndAirMiningSpeed(float constant){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.pendantSea)){
            return 1f;
        }
        return constant;
    }

    @Inject(method = "getMiningSpeedModifier", at = @At("RETURN"),cancellable = true)
    private void increaseMiningSpeed(CallbackInfoReturnable<Float> cir){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.digFaster)){
            cir.setReturnValue(cir.getReturnValue() * 1.25f);
        }
    }


    @Inject(method = "decreaseAirSupply", at = @At("HEAD"), cancellable = true)
    private void reduceAirDrain(int iAirSupply, CallbackInfoReturnable<Integer> cir){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.pendantSea)) {
            if(this.rand.nextInt(3) == 0) {
                cir.setReturnValue(iAirSupply);
            }
        }
    }
    @Inject(method = "onLivingUpdate", at = @At("HEAD"))
    private void onUpdateHook(CallbackInfo ci){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(this.isPotionActive(Potion.poison) && ACUtils.hasAccessory(obj,ACItems.bezoar)){
            this.removePotionEffect(Potion.poison.id);
        }
        if(this.prevTickYaw != obj.rotationYaw) {
            this.prevTickYaw = obj.rotationYaw;
        }
        if(this.prevTickPitch != obj.rotationPitch) {
            this.prevTickPitch = obj.rotationPitch;
        }
        ItemStack acc = ACUtils.findAccessory(obj, s -> s.getItem() == ACItems.velocityEnchantment);
        if (acc != null && acc.getItem() instanceof AccessoryItem item) {
            if (ACUtils.getActiveCooldown(obj, item) <= 0) {
                if (obj.isBlocking() && obj.isSwingInProgress) {
                    this.startSwordDash(obj); // no target dash
                    if (!obj.worldObj.isRemote) {
                        ACUtils.setAccessoryCooldown(obj, item, item.getFinalCooldown());
                    }
                }
            }
        }



        boolean hasWaterBoots = ACUtils.hasAccessory(obj, ACItems.waterWalkingBoots);
        boolean hasLavaBoots = ACUtils.hasAccessory(obj, ACItems.lavaWaders);

        if (hasWaterBoots || hasLavaBoots) {
            int x = MathHelper.floor_double(obj.posX);
            int y = MathHelper.floor_double(obj.boundingBox.minY - 0.1D);
            int z = MathHelper.floor_double(obj.posZ);

            Block blockBelow = Block.blocksList[obj.worldObj.getBlockId(x, y, z)];

            boolean isWater = blockBelow == Block.waterStill || blockBelow == Block.waterMoving;
            boolean isLava = blockBelow == Block.lavaStill || blockBelow == Block.lavaMoving;

            if ((isWater && hasWaterBoots) || (isLava && hasLavaBoots)) {
                if(obj.posY > y + 0.9) {
                    if (obj.motionY < 0) obj.motionY = 0;
                }

                obj.onGround = true;
                obj.fallDistance = 0F;

                obj.moveFlying(obj.moveStrafing, obj.moveForward, 0.02F);

                obj.posY = y + 0.9;
                if(hasLavaBoots && this.isBurning()){
                    obj.extinguish();
                }
            }
        }
        this.isImmuneToFire = hasLavaBoots;






        // ACCESSORY STUFF
        if(this.ticksExisted % 20 == 0){
            IInventory inv = ((IPlayerAccessories) obj).getAccessoryInventory();
            ((IPlayerAccessories) obj).updateAccessoriesFromInventory(inv);
        }
        ACUtils.decrementAllAccessoryCooldowns(obj);
    }
    @Inject(method = "canPlayerEdit", at = @At("HEAD"),cancellable = true)
    private void allowPlacingBlocksWhileAirborneIfAccessory(int par1, int par2, int par3, int par4, ItemStack par5ItemStack, CallbackInfoReturnable<Boolean> cir){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.mechanicalGlove)) {
            cir.setReturnValue(true);
        }
    }
    @ModifyConstant(method = "damageEntity", constant = @Constant(floatValue = 0.5f))
    private float manageWhetstone(float constant){
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.whetstone)) {
            return 0.35f;
        }
        return constant;
    }
    @Override
    public float getSlipperinessRelativeToBlock(int iBlockID) {
        EntityPlayer obj = ((EntityPlayer) (Object)this);

        if(ACUtils.hasAccessory(obj,ACItems.iceSkates)) {
            return 0.96f;
        }
        return super.getSlipperinessRelativeToBlock(iBlockID);
    }





    // HELPER METHODS FOR CONVENIENCE

    @Unique public float getDamageMultiplier(float currentYaw, float lastYaw, float currentPitch, float lastPitch) {
        float deltaYaw = wrapAngleTo180(currentYaw - lastYaw);
        float deltaPitch = currentPitch - lastPitch;

        float angularSpeed = (float)Math.sqrt(deltaYaw * deltaYaw + deltaPitch * deltaPitch);

        float multiplier = 1.0F + (angularSpeed * 0.015F);
        return Math.min(multiplier, 3.0F);
    }
    @Unique private float wrapAngleTo180(float angle) {
        angle %= 360.0F;
        if (angle >= 180.0F) angle -= 360.0F;
        if (angle < -180.0F) angle += 360.0F;
        return angle;
    }
    @Unique private boolean isBlockingWithSword(EntityPlayer obj){
        return obj.getHeldItem() != null && obj.getHeldItem().getItem() instanceof ItemSword && obj.isBlocking();
    }

    private void startSwordDash(EntityPlayer attacker) {
        ItemStack held = attacker.getHeldItem();
        if (held == null || !(held.getItem() instanceof ItemSword)) {
            return;
        }

        float yaw = attacker.rotationYaw;
        double dashSpeed = 1.8D;

        double motionX = -Math.sin(Math.toRadians(yaw)) * dashSpeed;
        double motionZ =  Math.cos(Math.toRadians(yaw)) * dashSpeed;

        attacker.motionX = motionX;
        attacker.motionZ = motionZ;
        attacker.motionY = 0.3D;

        attacker.velocityChanged = true;
    }











}

