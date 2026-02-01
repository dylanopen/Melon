package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.clear.Clear;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

import java.util.List;

public class CmdClearhotbar {
    private static final int HOTBAR_START = 0; // inclusive
    private static final int HOTBAR_END = 9; // exclusive

    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            int itemsRemoved = Clear.slotRange(player.getInventory(), HOTBAR_START, HOTBAR_END);
            Clear.runCommandMeta(ctx, player, itemsRemoved);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            return 0;
        }
        int itemsRemoved = Clear.slotRange(player.getInventory(), HOTBAR_START, HOTBAR_END);
        Clear.runCommandMeta(ctx, player, itemsRemoved);
        return Command.SINGLE_SUCCESS;
    }
}
