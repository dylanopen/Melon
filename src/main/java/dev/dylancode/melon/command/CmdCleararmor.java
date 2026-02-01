package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.clear.Clear;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.entity.Player;

import java.util.List;

public class CmdCleararmor {
    private static final int ARMOR_START = 36; // inclusive
    private static final int ARMOR_END = 40; // exclusive

    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            int itemsRemoved = Clear.slotRange(player.getInventory(), ARMOR_START, ARMOR_END);
            Clear.runCommandMeta(ctx, player, itemsRemoved);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            return 0;
        }
        int itemsRemoved = Clear.slotRange(player.getInventory(), ARMOR_START, ARMOR_END);
        Clear.runCommandMeta(ctx, player, itemsRemoved);
        return Command.SINGLE_SUCCESS;
    }
}
