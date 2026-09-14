package net.fabricmc.accessories.mixin;

import api.item.items.AxeItem;
import btw.community.accessories.ACUtils;
import btw.item.BTWItems;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(BlockLog.class)
public abstract class BlockLogMixin extends BlockRotatedPillar {
    @Unique private boolean wasEfficientlyMined = false;

    protected BlockLogMixin(int i, Material material) {
        super(i, material);
    }
    @Inject(method = "quantityDropped", at = @At("TAIL"),cancellable = true)
    private void allowAccessoryBreaks(Random par1Random, CallbackInfoReturnable<Integer> cir){
        if(this.wasEfficientlyMined){
            cir.setReturnValue(0);
            this.wasEfficientlyMined = false;
        }
    }
    @Inject(method = "breakBlock", at = @At("HEAD"))
    private void additionalBlocks(World world, int x, int y, int z, int blockID, int metadata, CallbackInfo ci){
        int meta = metadata & 3;
        EntityPlayer player = world.getClosestPlayer(x,y,z, 7);
        if(player == null) return;
        ItemStack held = player.getHeldItem();
        if(held == null) return;
        if (held.getItem() instanceof AxeItem axe && ACUtils.hasAccessory(player, ACItems.woodEnchantment)) {
            this.wasEfficientlyMined = true;
            if (axe == Item.axeStone) {
                dropItem(world, x, y, z, new ItemStack(Item.stick, 4));
                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 6));
                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 4, meta));
                return;

            }

            if (axe == Item.axeIron) {
                dropItem(world, x, y, z, new ItemStack(Item.stick, 6));
                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 4));
                dropItem(world, x, y, z, new ItemStack(Block.planks, 2, meta));
                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 4, meta));
                return;


            }
            if (axe == Item.axeGold) {
                dropItem(world, x, y, z, new ItemStack(Item.stick, 8));
                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 16));
                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 8, meta));
                dropItem(world, x, y, z, new ItemStack(Block.planks, 2, meta));
                return;
            }

            if (axe == Item.axeDiamond) {
                dropItem(world, x, y, z, new ItemStack(Block.planks, 6, meta));
                return;

            }

            if (axe == BTWItems.steelAxe) {
                dropItem(world, x, y, z, new ItemStack(Block.planks, 8, meta));
                return;

            }
            if (axe.toolMaterial.getHarvestLevel() > 2) {
                dropItem(world, x, y, z, new ItemStack(Block.planks, 4, meta));
            }

        }
    }
//
//    @Override
//    public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
//
//        ItemStack held = player.getHeldItem();
//        if (held.getItem() instanceof ItemAxe axe && ACUtils.hasAccessory(player, ACItems.woodEnchantment)) {
//            if (axe == Item.axeStone) {
//                dropItem(world, x, y, z, new ItemStack(Item.stick, 4));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 6));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 4, meta));
//            }
//            if (axe == Item.axeIron) {
//                dropItem(world, x, y, z, new ItemStack(Item.stick, 6));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 4));
//                dropItem(world, x, y, z, new ItemStack(Block.planks, 2, meta));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 4, meta));
//
//            }
//            if (axe == Item.axeGold) {
//                dropItem(world, x, y, z, new ItemStack(Item.stick, 8));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.sawDust, 16));
//                dropItem(world, x, y, z, new ItemStack(BTWItems.bark, 8, meta));
//                dropItem(world, x, y, z, new ItemStack(Block.planks, 2, meta));
//            }
//            if (axe == Item.axeDiamond) {
//                dropItem(world, x, y, z, new ItemStack(Block.planks, 6, meta));
//            }
//            if (axe == BTWItems.steelAxe) {
//                dropItem(world, x, y, z, new ItemStack(Block.planks, 8, meta));
//            }
//            player.addHarvestBlockExhaustion(this.blockID, x,y, z,meta);
//        } else {
//            super.harvestBlock(world, player, x, y, z, meta);
//        }
//    }


    @Unique
    private void dropItem(World world, int x, int y, int z, ItemStack stack) {
        EntityItem entityitem = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, stack);
        world.spawnEntityInWorld(entityitem);
    }

}
