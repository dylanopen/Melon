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

public class CmdGms {
    public static void setGamemodeSurvival(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(formatMessage("Set " + player.getName() + "'s gamemode to SURVIVAL"));
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this command without arguments!"));
            return Command.SINGLE_SUCCESS;
        }
        setGamemodeSurvival(player);
        return Command.SINGLE_SUCCESS;
    }

    public static int executeOthers(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (final Player player : players) {
            setGamemodeSurvival(player);
        }
        return Command.SINGLE_SUCCESS;
    }
}
