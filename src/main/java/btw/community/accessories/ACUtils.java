package btw.community.accessories;

import net.fabricmc.accessories.IPlayerAccessories;
import net.fabricmc.accessories.items.AccessoryItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ACUtils {

    public static final String NBT_ACCESSORY_TAG = "Accessories"; // unused field
    public static final int MAX_ACCESSORY_SLOTS = 5; // unused field

    public static ItemStack getAccessoryAt(EntityPlayer player, int slot) {
        if (player instanceof IPlayerAccessories) {
            ItemStack[] accessories = ((IPlayerAccessories) player).getAccessories();
            if (slot >= 0 && slot < accessories.length) {
                return accessories[slot];
            }
        }
        return null;
    }


    public static boolean hasAccessory(EntityPlayer player, Item item) {
        if (player instanceof IPlayerAccessories) {
            for (ItemStack stack : ((IPlayerAccessories) player).getAccessories()) {
                if (stack != null && stack.getItem() == item) {
                    return true;
                }
            }
        }
        return false;
    }
    private static ItemStack[] getAccessoriesArray(EntityPlayer player) {
        if (player instanceof IPlayerAccessories) {
            ItemStack[] arr = ((IPlayerAccessories) player).getAccessories();
            return arr == null ? new ItemStack[0] : arr;
        }
        return new ItemStack[0];
    }


    public static boolean hasAnyAccessory(EntityPlayer player, Item... items) {
        if (items == null || items.length == 0) return false;
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack == null) continue;
            Item it = stack.getItem();
            for (Item candidate : items) {
                if (candidate != null && it == candidate) return true;
            }
        }
        return false;
    }


    public static ItemStack findAccessory(EntityPlayer player, java.util.function.Predicate<ItemStack> condition) {
        if (condition == null) return null;
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack != null && condition.test(stack)) return stack;
        }
        return null;
    }


    public static java.util.List<ItemStack> getAllAccessories(EntityPlayer player) {
        java.util.List<ItemStack> out = new java.util.ArrayList<>();
        for (ItemStack stack : getAccessoriesArray(player)) {
            if (stack != null) out.add(stack);
        }
        return out;
    }
    public static java.util.List<ItemStack> getAllAccessoriesWithCooldowns(EntityPlayer player) {
        java.util.List<ItemStack> out = new java.util.ArrayList<>();
        for (ItemStack stack : getAllAccessories(player)) {
            if (hasCooldown(stack)) out.add(stack);
        }
        return out;
    }

    public static boolean hasCooldown(ItemStack stack) {
        if (stack == null) return false;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return false;
        return ((AccessoryItem) it).getFinalCooldown() >= 0;
    }

    public static boolean hasCooldownItem(Item item) {
        if (!(item instanceof AccessoryItem)) return false;
        return ((AccessoryItem) item).getFinalCooldown() >= 0;
    }


    public static void decrementAllAccessoryCooldowns(EntityPlayer player) {
        for (ItemStack stack : getAllAccessoriesWithCooldowns(player)) {
            Item it = stack.getItem();
            if (!(it instanceof AccessoryItem)) continue;
            AccessoryItem accessory = (AccessoryItem) it;
            int cd = accessory.getCooldown();
            if (cd > 0) accessory.setCooldown(cd - 1);
        }
    }


    public static void resetAccessoryCooldown(EntityPlayer player, Item accessoryItem) {
        if (accessoryItem == null) return;
        ItemStack stack = findAccessory(player, s -> s.getItem() == accessoryItem);
        if (stack == null) return;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return;
        AccessoryItem acc = (AccessoryItem) it;
        acc.setCooldown(acc.getFinalCooldown());
    }

    public static int getActiveCooldown(EntityPlayer player, Item accessoryItem) {
        if (accessoryItem == null) return -1;
        ItemStack stack = findAccessory(player, s -> s.getItem() == accessoryItem);
        if (stack == null) return -1;
        Item it = stack.getItem();
        if (!(it instanceof AccessoryItem)) return -1;
        return ((AccessoryItem) it).getCooldown();
    }

    public static boolean isAccessoryReady(EntityPlayer player, AccessoryItem accessoryItem) {
        if (accessoryItem == null) return false;
        return hasAccessory(player, accessoryItem) && hasCooldownItem(accessoryItem) && accessoryItem.getCooldown() == 0;
    }


}
