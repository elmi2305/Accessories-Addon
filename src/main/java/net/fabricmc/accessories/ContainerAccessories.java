package net.fabricmc.accessories;

import net.minecraft.src.*;
public class ContainerAccessories extends Container {

    private final EntityPlayer thePlayer;
    private final IInventory accessoryInv; // your custom accessory inventory

    private final int accessoryCount;

    public ContainerAccessories(InventoryPlayer playerInv, IInventory accessoryInv, EntityPlayer player, int accessoryCount) {
        this.thePlayer = player;
        this.accessoryInv = accessoryInv;
        this.accessoryCount = Math.min(accessoryInv.getSizeInventory(), accessoryCount);

        // Slots are a horizontal row so every supported capacity fits the same inventory width.
        for (int i = 0; i < this.accessoryCount; i++) {
            this.addSlotToContainer(new SlotAccessory(accessoryInv, i, 8 + i * 18, 18));
        }

        // ---- 2. Add player inventory (same as ContainerPlayer, MUST match) ----

        // Player main inventory (3 rows × 9 columns)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlotToContainer(new Slot(
                        playerInv,
                        col + (row + 1) * 9,
                        8 + col * 18,
                        84 + row * 18
                ));
            }
        }

        // Player hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlotToContainer(new Slot(
                    playerInv,
                    col,
                    8 + col * 18,
                    142
            ));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

    public int getAccessoryCount() {
        return accessoryCount;
    }

    // ---- 3. Item shift-clicking support ----
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack original = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            original = stack.copy();

            // slot ranges:
            // 0 → accessoryCount-1: accessory slots
            // accessoryCount → end: player inventory

            if (index < accessoryCount) {
                // from accessory → to player inventory
                if (!this.mergeItemStack(stack,
                        accessoryCount,
                        this.inventorySlots.size(),
                        true)) {
                    return null;
                }
            } else {
                // from player inventory → accessory slots
                if (!this.mergeItemStack(stack, 0, accessoryCount, false)) {
                    return null;
                }
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (stack.stackSize == original.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stack);
        }

        return original;
    }

    // Prevent putting items into invalid slots
    @Override
    public boolean func_94530_a(ItemStack stack, Slot slot) {
        return super.func_94530_a(stack, slot);
    }
}
