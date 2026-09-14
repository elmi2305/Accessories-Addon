package net.fabricmc.accessories.mixin;

import btw.item.BTWItems;
import net.fabricmc.accessories.items.ACItems;
import net.minecraft.src.BlockBreakable;
import net.minecraft.src.BlockGlass;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockGlass.class)
public class BlockGlassMixin extends BlockBreakable {
    protected BlockGlassMixin(int i, String string, Material material, boolean bl) {
        super(i, string, material, bl);
    }

    @Override
    public boolean dropComponentItemsOnBadBreak(World world, int i, int j, int k, int iMetadata, float fChanceOfDrop) {
        this.dropItemsIndividually(world, i, j, k, ACItems.glassShard.itemID, world.rand.nextInt(2) + 2, 0, fChanceOfDrop);
        return super.dropComponentItemsOnBadBreak(world, i, j, k, iMetadata, fChanceOfDrop);
    }
}
