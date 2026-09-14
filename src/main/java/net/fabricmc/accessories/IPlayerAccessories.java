package net.fabricmc.accessories;

import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryBasic;
import net.minecraft.src.ItemStack;

public interface IPlayerAccessories {
    ItemStack[] getAccessories(); // Access to raw array
    void setAccessories(ItemStack[] accessories);

    InventoryBasic getAccessoryInventoryBacking(); // NEW getter
    void setAccessoryInventoryBacking(InventoryBasic inv); // NEW setter

    default IInventory getAccessoryInventory() {
        return getAccessoryInventoryBacking(); // always return same instance
    }

    default void updateAccessoriesFromInventory(IInventory inv) {
        ItemStack[] acc = new ItemStack[inv.getSizeInventory()];
        for (int i = 0; i < acc.length; i++) {
            acc[i] = inv.getStackInSlot(i);
        }
        setAccessories(acc);
    }
}
