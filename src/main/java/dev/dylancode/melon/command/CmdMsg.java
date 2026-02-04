package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.placeholders.PlayerPlaceholders;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class CmdMsg {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        String rawMessage = ctx.getArgument("message", String.class);
        String executor = ctx.getSource().getSender().getName();

        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.putAll(Map.ofEntries(
                    Map.entry("sender", executor),
                    Map.entry("message", rawMessage)
            ));
            placeholders.putAll(PlayerPlaceholders.get(player, "receiver-"));
            Component receiveMessage = miniMessage().deserialize(applyPlaceholders(MessagesConfig.receiveMsg, placeholders));
            Component confirmMessage = miniMessage().deserialize(applyPlaceholders(MessagesConfig.confirmMsg, placeholders));
            ctx.getSource().getSender().sendMessage(confirmMessage);
            player.sendMessage(receiveMessage);
        }

        return Command.SINGLE_SUCCESS;
    }
}
