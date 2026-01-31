package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdGm {
    public static void setPlayerGamemode(CommandContext<CommandSourceStack> ctx, Player player, GameMode gamemode) {
        player.setGameMode(gamemode);
        HashMap<String, String> placeholders = new HashMap<>(Map.ofEntries(
                Map.entry("sender", ctx.getSource().getSender().getName()),
                Map.entry("receiver", player.getName()),
                Map.entry("gamemode", gamemode.name().toLowerCase())
        ));
        Component confirmation = formatMessage(applyPlaceholders(MessagesConfig.confirmGamemode, placeholders));
        player.sendMessage(confirmation);
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx, GameMode gamemode) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this command without arguments!"));
            return Command.SINGLE_SUCCESS;
        }
        setPlayerGamemode(ctx, player, gamemode);
        return Command.SINGLE_SUCCESS;
    }

    public static int executeOthers(CommandContext<CommandSourceStack> ctx, GameMode gamemode) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (final Player player : players) {
            setPlayerGamemode(ctx, player, gamemode);
        }
        return Command.SINGLE_SUCCESS;
    }
}
