package net.fabricmc.accessories.items;

import api.world.data.DataEntry;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/** A one-use, server-authoritative accessory capacity unlock. */
public class ItemAccessorySlotUpgrade extends Item {
    private final DataEntry.PlayerDataEntry<Boolean> unlockFlag;

    public ItemAccessorySlotUpgrade(int id, DataEntry.PlayerDataEntry<Boolean> unlockFlag) {
        super(id);
        this.unlockFlag = unlockFlag;
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            // Setting a boolean rather than incrementing a counter makes copied rewards harmless.
            player.setData(unlockFlag, true);
            stack.stackSize--;
        }
        return stack;
    }
}
