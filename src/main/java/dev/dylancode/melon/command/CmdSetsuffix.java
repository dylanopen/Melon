package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.customname.CustomNameStorage;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdSetsuffix {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());
        final String suffix = ctx.getArgument("suffix", String.class);
        for (Player player : players) {
            HashMap<String, String> placeholders = getPlaceholders(ctx, player, suffix);
            CustomNameStorage.setSuffix(player.getUniqueId(), suffix);
            sender.sendMessage(formatMessage(applyPlaceholders(Objects.requireNonNull(MessagesConfig.confirmSetsuffix), placeholders)));
        }
        return Command.SINGLE_SUCCESS;
    }

    private static @NotNull HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx, Player player, String suffix) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("receiver", player.getName());
        placeholders.put("suffix", suffix);
        return placeholders;
    }
}
