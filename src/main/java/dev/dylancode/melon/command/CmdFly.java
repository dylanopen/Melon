package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdFly {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this!"));
            return Command.SINGLE_SUCCESS;
        }
        HashMap<String, String> placeholders = new HashMap<>();
        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            player.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmFlyDisable, placeholders)));
        } else {
            player.setAllowFlight(true);
            player.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmFlyEnable, placeholders)));
        }
        return Command.SINGLE_SUCCESS;
    }
}
