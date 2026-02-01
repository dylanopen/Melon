package dev.dylancode.melon.clear;

import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

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

    public static int all(Inventory inventory) {
        int itemsRemoved = 0;
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            itemsRemoved += slot(inventory, slot);
        }
        return itemsRemoved;
    }

    public static int slotRange(Inventory inventory, int startSlot, int endSlot) {
        int itemsRemoved = 0;
        for (int slot = startSlot; slot < endSlot; slot++) {
            itemsRemoved += slot(inventory, slot);
        }
        return itemsRemoved;
    }

    public static int runCommandMeta(CommandContext<CommandSourceStack> ctx, Player player, int itemsRemoved) {
        HashMap<String, String> placeholders = getPlaceholders(ctx, player, itemsRemoved);
        ctx.getSource().getSender().sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmClear, placeholders)));
        return itemsRemoved;
    }

    public static @NotNull HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx, Player player, int itemsRemoved) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("items-removed", String.valueOf(itemsRemoved));
        return placeholders;
    }
}
