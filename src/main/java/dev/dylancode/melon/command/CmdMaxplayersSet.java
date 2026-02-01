package dev.dylancode.melon.command;

import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;

public class CmdMaxplayersSet {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        int maxPlayers = ctx.getArgument("player-limit", Integer.class);
        int oldMaxPlayers = Bukkit.getMaxPlayers();
        Bukkit.setMaxPlayers(maxPlayers);
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("max-players", String.valueOf(maxPlayers));
        placeholders.put("old-max-players", String.valueOf(oldMaxPlayers));
        String response = applyPlaceholders(MessagesConfig.confirmMaxplayers, placeholders);
        ctx.getSource().getSender().sendMessage(MessagesConfig.formatMessage(response));
        return 1;
    }
}
