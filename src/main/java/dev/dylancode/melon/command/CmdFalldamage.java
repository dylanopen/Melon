package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.util.TriState;
import org.bukkit.entity.Player;

import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdFalldamage {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this!"));
            return Command.SINGLE_SUCCESS;
        }
        if (player.hasFlyingFallDamage().equals(TriState.TRUE)) {
            player.setFlyingFallDamage(TriState.FALSE);
            player.sendMessage(formatMessage(MessagesConfig.confirmFlyFallDamageDisable));
        } else {
            player.setFlyingFallDamage(TriState.TRUE);
            player.sendMessage(formatMessage(MessagesConfig.confirmFlyFallDamageEnable));
        }
        return Command.SINGLE_SUCCESS;
    }
}
