package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.broadcast.BroadcastMessage;
import dev.dylancode.melon.clear.Clear;
import dev.dylancode.melon.config.BroadcastConfig;
import dev.dylancode.melon.placeholders.PlayerPlaceholders;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdFakejoin {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            run(player);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player)ctx.getSource().getExecutor();
        run(player);
        return Command.SINGLE_SUCCESS;
    }

    public static void run(Player player) {
        HashMap<String, String> placeholders = PlayerPlaceholders.get(player);
        new BroadcastMessage(formatMessage(applyPlaceholders(BroadcastConfig.playerJoin, placeholders)));
    }
}
