package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class CmdKick {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        String miniReason = ctx.getArgument("reason", String.class);
        String executor = ctx.getSource().getSender().getName();
        String kickMessage = applyPlaceholders(MessagesConfig.kickMessage, new HashMap<>(Map.ofEntries(
                Map.entry("executor", executor),
                Map.entry("reason", miniReason)
        )));

        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            player.kick(miniMessage().deserialize(kickMessage));
        }

        return Command.SINGLE_SUCCESS;
    }
}
