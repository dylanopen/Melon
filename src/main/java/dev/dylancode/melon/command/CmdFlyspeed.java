package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdFlyspeed {
    public static final float MIN_FLYSPEED = -100.0f;
    public static final float MAX_FLYSPEED = 100.0f;

    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this!"));
            return Command.SINGLE_SUCCESS;
        }
        float speed = FloatArgumentType.getFloat(ctx, "speed percentage") * 0.01f;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(0);
        String speedString = decimalFormat.format(speed*100.0f);
        player.setFlySpeed(speed);
        HashMap<String, String> placeholders = new HashMap<>(Map.ofEntries(
                Map.entry("sender", player.getName()),
                Map.entry("speed", speedString)
        ));
        Component msgConfirm = formatMessage(applyPlaceholders(MessagesConfig.confirmFlyspeed, placeholders));
        player.sendMessage(msgConfirm);
        return Command.SINGLE_SUCCESS;
    }
}
