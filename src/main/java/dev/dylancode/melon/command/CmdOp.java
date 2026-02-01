package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdOp {

    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        String executor = ctx.getSource().getSender().getName();
        final PlayerSelectorArgumentResolver targetResolver = ctx.getArgument("players", PlayerSelectorArgumentResolver.class);
        final List<Player> players = targetResolver.resolve(ctx.getSource());

        for (Player player : players) {
            HashMap<String, String> placeholders = new HashMap<>(Map.ofEntries(
                    Map.entry("sender", executor),
                    Map.entry("receiver", player.getName())
            ));
            if (player.isOp()) {
                Component msgAlreadyOp = formatMessage(applyPlaceholders(MessagesConfig.confirmAlreadyOp, placeholders));
                ctx.getSource().getSender().sendMessage(msgAlreadyOp);
                continue;
            }
            Component msgReceive = formatMessage(applyPlaceholders(MessagesConfig.receiveOp, placeholders));
            Component msgConfirm = formatMessage(applyPlaceholders(MessagesConfig.confirmOp, placeholders));

            player.setOp(true);
            ctx.getSource().getSender().sendMessage(msgConfirm);
            player.sendMessage(msgReceive);
        }

        return Command.SINGLE_SUCCESS;
    }
}
