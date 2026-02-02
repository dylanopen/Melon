package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.health.PlayerHealth;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdDamage {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        final int damageHP = ctx.getArgument("hp", Integer.class);
        for (Player player : players) {
            HashMap<String, String> placeholders = getPlaceholders(ctx, player, damageHP);
            PlayerHealth.damage(player, damageHP);
            sender.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmDamage, placeholders)));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static @NotNull HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx, Player player, double hp) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("hp", String.valueOf((int)Math.round(hp)));
        return placeholders;
    }
}
