package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdHealthSet {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        final double health = ctx.getArgument("hp", Double.class);
        for (Player player : players) {
            if (health > player.getHealthScale()) {
                sender.sendMessage(Component.text("Cannot set " + player.getName() + "'s health above their healthscale", NamedTextColor.RED));
                continue;
            }
            HashMap<String, String> placeholders = getPlaceholders(ctx, player, health);
            player.setHealth(health);
            sender.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmHealth, placeholders)));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static @NotNull HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx, Player player, double health) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("hp", String.valueOf(Math.round(health)));
        return placeholders;
    }
}
