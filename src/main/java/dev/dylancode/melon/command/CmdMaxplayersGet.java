package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;

public class CmdMaxplayersGet {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        int maxPlayers = Bukkit.getMaxPlayers();
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("max-players", String.valueOf(maxPlayers));
        String response = applyPlaceholders(MessagesConfig.queryMaxplayers, placeholders);
        ctx.getSource().getSender().sendMessage(MessagesConfig.formatMessage(response));
        return Command.SINGLE_SUCCESS;
    }
}
