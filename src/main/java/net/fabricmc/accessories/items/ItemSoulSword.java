package net.fabricmc.accessories.items;

import net.minecraft.src.*;
import net.fabricmc.accessories.ISoulSwordCombatState;

import java.util.ArrayList;
import java.util.List;

public class ItemSoulSword extends ItemSword {
    public static final int MAX_DURABILITY = 10001;
    public static final float BASE_ATTACK_DAMAGE = 4.0F;
    public static final float MIN_ATTACK_DAMAGE = 1.0F;
    public static final float MAX_ATTACK_DAMAGE = 9.0F;

    private final List<String> tooltipLines = new ArrayList<String>();

    public ItemSoulSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setMaxDamage(MAX_DURABILITY);
    }

    public ItemSoulSword addTooltip(String line) {
        this.tooltipLines.add(line);
        return this;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.addAll(this.tooltipLines);
        super.addInformation(stack, player, list, advanced);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.setItemDamage(getInitialItemDamage());
        super.onCreated(stack, world, player);
    }

    public int getInitialItemDamage() {
        return this.getMaxDamage() - 1;
    }

    public float getAttackDamage(ItemStack stack) {
        int maximumDamage = stack.getMaxDamage();
        int currentDamage = MathHelper.clamp_int(stack.getItemDamage(), 0, maximumDamage - 1);
        float repairProgress = (float)(maximumDamage - 1 - currentDamage) / (float)(maximumDamage - 1);
        return MIN_ATTACK_DAMAGE + (MAX_ATTACK_DAMAGE - MIN_ATTACK_DAMAGE) * repairProgress;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player) {
        if (!player.worldObj.isRemote && ((ISoulSwordCombatState)player).accessories$canRepairSoulSword()) {
            if (stack.getItemDamage() > 0) {
                stack.setItemDamage(stack.getItemDamage() - 1);
            } else if (entityHit.hurtResistantTime == entityHit.maxHurtResistantTime && player.rand.nextInt(3) == 0) {
                entityHit.dropFewItems(true, EnchantmentHelper.getLootingModifier(player));
            }
        }
        return true;
    }
}
