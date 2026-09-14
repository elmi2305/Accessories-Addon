package net.fabricmc.accessories.items;

import net.minecraft.src.*;

public class ItemSoulSword extends ItemSword {
    private boolean unbreakable;

    public ItemSoulSword(int par1, EnumToolMaterial par2EnumToolMaterial) {
        super(par1, par2EnumToolMaterial);
        this.setMaxDamage(10001);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        stack.setItemDamage(stack.getMaxDamage() - 10);
        super.onCreated(stack, world, player);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityHit, EntityLivingBase player) {
        if (entityHit.hurtResistantTime == 20) {
            if(stack.getItemDamage() == 0){
                this.unbreakable = true;
                int looting = EnchantmentHelper.getLootingModifier(player);
                if (!player.worldObj.isRemote) {
                    if (player.rand.nextInt(3) == 0) {
                        entityHit.dropFewItems(true, looting);
                    }
                }
                return true;
            }
            if (!player.worldObj.isRemote) {
                int sharpness = EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack);
                int smite = EnchantmentHelper.getEnchantmentLevel(Enchantment.smite.effectId, stack);
                stack.setItemDamage(stack.getItemDamage() - player.rand.nextInt(4) - 1 - player.rand.nextInt(sharpness * 2 + smite + 1));
                System.out.println(stack.getItemDamage());
            }
        }

        return super.hitEntity(stack, entityHit, player);
    }

    @Override
    public boolean isDamageable() {
        if(this.unbreakable){
            System.out.println("unbreakable");
            return false;
        }
        return super.isDamageable();
    }
}
