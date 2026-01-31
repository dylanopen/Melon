package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
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
        String rawReceivedMessage = applyPlaceholders(MessagesConfig.msgReceiveMessage, new HashMap<>(Map.ofEntries(
                Map.entry("sender", executor),
                Map.entry("message", rawMessage)
        )));
        Component receivedMessage = miniMessage().deserialize(rawReceivedMessage);

        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            player.sendMessage(receivedMessage);
        }

        return Command.SINGLE_SUCCESS;
    }
}
