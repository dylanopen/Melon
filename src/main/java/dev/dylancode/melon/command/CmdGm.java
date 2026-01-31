package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdGm {
    public static void setPlayerGamemode(Player player, GameMode gamemode) {
        player.setGameMode(gamemode);
        player.sendMessage(formatMessage("Set " + player.getName() + "'s gamemode to " + gamemode));
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx, GameMode gamemode) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this command without arguments!"));
            return Command.SINGLE_SUCCESS;
        }
        setPlayerGamemode(player, gamemode);
        return Command.SINGLE_SUCCESS;
    }

    public static int executeOthers(CommandContext<CommandSourceStack> ctx, GameMode gamemode) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (final Player player : players) {
            setPlayerGamemode(player, gamemode);
        }
        return Command.SINGLE_SUCCESS;
    }
}
