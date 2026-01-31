package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
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
            player.sendMessage(formatMessage("Toggled flying fall damage OFF"));
        } else {
            player.setFlyingFallDamage(TriState.TRUE);
            player.sendMessage(formatMessage("Toggled flying fall damage ON"));
        }
        return Command.SINGLE_SUCCESS;
    }
}
