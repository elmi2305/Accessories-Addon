package net.fabricmc.accessories;

import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;

public class SlotAccessory extends Slot {
    public SlotAccessory(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack != null && stack.getItem() instanceof AccessoryItem;
    }

    @Override
    public void putStack(ItemStack stack) {
        if (stack != null && stack.stackSize > 1) {
            stack = stack.copy();
            stack.stackSize = 1;
        }
        super.putStack(stack);
        onSlotChanged();
    }

    @Override
    public void onSlotChanged() {
        super.onSlotChanged();
        ItemStack stack = this.getStack();
        if (stack != null) {
            System.out.println("Placed in accessory slot: " + stack.getDisplayName());
        }
    }
}
