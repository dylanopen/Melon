package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.health.PlayerHealth;
import dev.dylancode.melon.health.PlayerMaxHealth;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdHeal {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        final int healHP = ctx.getArgument("hp", Integer.class);

        for (Player player : players) {
            run(sender, player, healHP);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelf(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        Player player = (Player)ctx.getSource().getExecutor();
        final int healHP = ctx.getArgument("hp", Integer.class);
        run(sender, player, healHP);
        return Command.SINGLE_SUCCESS;
    }

    public static int executeFull(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        for (Player player : players) {
            final int healHP = PlayerMaxHealth.get(player) - PlayerHealth.get(player);
            run(sender, player, healHP);
        }
        return Command.SINGLE_SUCCESS;
    }

    public static int executeSelfFull(CommandContext<CommandSourceStack> ctx) {
        CommandSender sender = ctx.getSource().getSender();
        Player player = (Player)ctx.getSource().getExecutor();
        final int healHP = PlayerMaxHealth.get(player) - PlayerHealth.get(player);
        run(sender, player, healHP);
        return Command.SINGLE_SUCCESS;
    }

    public static void run(CommandSender sender, Player player, int healHP) {
        HashMap<String, String> placeholders = getPlaceholders(sender, player, healHP);
        PlayerHealth.heal(player, healHP);
        sender.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmHeal, placeholders)));
    }

    private static @NotNull HashMap<String, String> getPlaceholders(CommandSender sender, Player player, double hp) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", sender.getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("hp", String.valueOf((int)Math.round(hp)));
        return placeholders;
    }
}
