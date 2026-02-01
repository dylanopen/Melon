package dev.dylancode.melon.clear;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Clear {
    public static int slot(Inventory inventory, int slot) {
        int itemsRemoved = 0;
        ItemStack item = inventory.getItem(slot);
        if (item != null) {
            itemsRemoved += item.getAmount();
            inventory.clear(slot);
        }
        return itemsRemoved;
    }
}
