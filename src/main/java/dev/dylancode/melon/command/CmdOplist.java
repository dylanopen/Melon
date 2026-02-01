package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdOplist {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        StringBuilder opList = new StringBuilder();
        for (OfflinePlayer player : Bukkit.getOperators()) {
            String playerName = player.getName();
            String playerUuid = player.getUniqueId().toString();
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("sender", ctx.getSource().getSender().getName());
            placeholders.put("player-name", playerName);
            placeholders.put("player-uuid", playerUuid);
            opList.append(applyPlaceholders(MessagesConfig.listOperator, placeholders));
        }

        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("op-list", opList.toString());
        placeholders.put("op-count", String.valueOf(Bukkit.getOperators().size()));

        Component response = formatMessage(applyPlaceholders(MessagesConfig.queryOplist, placeholders));
        ctx.getSource().getSender().sendMessage(response);
        return Command.SINGLE_SUCCESS;
    }
}
