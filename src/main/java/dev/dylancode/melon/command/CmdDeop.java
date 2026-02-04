package dev.dylancode.melon.command;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.PlayerProfileListResolver;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdDeop {

    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        String executor = ctx.getSource().getSender().getName();
        final PlayerProfileListResolver targetResolver = ctx.getArgument("players", PlayerProfileListResolver.class);
        final Collection<PlayerProfile> players = targetResolver.resolve(ctx.getSource());

        for (PlayerProfile playerProfile : players) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(playerProfile.getId());
            HashMap<String, String> placeholders = new HashMap<>(Map.ofEntries(
                    Map.entry("sender", executor),
                    Map.entry("receiver", player.getName())
            ));
            if (!player.isOp()) {
                Component msgNotOp = formatMessage(applyPlaceholders(MessagesConfig.confirmNotOp, placeholders));
                ctx.getSource().getSender().sendMessage(msgNotOp);
                continue;
            }
            player.setOp(false);

            Component msgConfirm = formatMessage(applyPlaceholders(MessagesConfig.confirmDeop, placeholders));
            ctx.getSource().getSender().sendMessage(msgConfirm);
            if (player.isOnline()) {
                Component msgReceive = formatMessage(applyPlaceholders(MessagesConfig.receiveDeop, placeholders));
                ((Player)player).sendMessage(msgReceive);
            }
        }

        return Command.SINGLE_SUCCESS;
    }
}
