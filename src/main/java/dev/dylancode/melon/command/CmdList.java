package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdList {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        StringBuilder playerList = new StringBuilder();
        for (Player player : Bukkit.getOnlinePlayers()) {
            String playerName = player.getName();
            String playerUuuid = player.getUniqueId().toString();
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("sender", ctx.getSource().getSender().getName());
            placeholders.put("player-name", playerName);
            placeholders.put("player-uuid", playerUuuid);
            playerList.append(applyPlaceholders(MessagesConfig.listPlayer, placeholders));
        }
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("player-list", playerList.toString());
        placeholders.put("player-count", String.valueOf(Bukkit.getOnlinePlayers().size()));
        placeholders.put("max-player-count", String.valueOf(Bukkit.getMaxPlayers()));
        Component response = formatMessage(applyPlaceholders(MessagesConfig.queryList, placeholders));
        ctx.getSource().getSender().sendMessage(response);
        return Command.SINGLE_SUCCESS;
    }
}
