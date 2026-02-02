package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.ping.Latency;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdPing {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerSelectorArgumentResolver resolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        List<Player> players = resolver.resolve(ctx.getSource());

        for (Player player : players) {
            CmdPing.run(ctx, player);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player)ctx.getSource().getExecutor();
        run(ctx, player);
        return Command.SINGLE_SUCCESS;
    }

    public static void run(CommandContext<CommandSourceStack> ctx, Player player) {
        int ping = Latency.getMillis(player);
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("latency", String.valueOf(ping));
        ctx.getSource().getSender().sendMessage(formatMessage(applyPlaceholders(MessagesConfig.queryPing, placeholders)));
    }
}
