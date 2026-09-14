package net.fabricmc.accessories.items;

import api.world.data.DataEntry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * A one-time, per-player accessory capacity unlock delivered as food.
 *
 * The unlock is stored in synchronized player data, so the same fruit can be
 * awarded to every player without allowing any player to unlock a slot twice.
 */
public class ItemAccessorySlotFruit extends ItemFood {
    private final DataEntry.PlayerDataEntry<Boolean> unlockFlag;

    public ItemAccessorySlotFruit(int id, DataEntry.PlayerDataEntry<Boolean> unlockFlag) {
        super(id, 2, 0.3F, false);
        this.unlockFlag = unlockFlag;
        this.setMaxStackSize(1);
        this.setAlwaysEdible();
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // Do not start eating (or consume) a duplicate reward for this player.
        if (player.getData(unlockFlag)) {
            return stack;
        }

        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        // Revalidate on the authoritative side in case the client had stale
        // synchronized progress or a second consume completed first.
        if (!world.isRemote) {
            if (player.getData(unlockFlag)) {
                return stack;
            }

            player.setData(unlockFlag, true);
        }

        return super.onEaten(stack, world, player);
    }
}
