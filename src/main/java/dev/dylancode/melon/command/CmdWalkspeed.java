package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdWalkspeed {
    public static final float MIN_WALKSPEED = 0.0f;
    public static final float MAX_WALKSPEED = 100.0f;

    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(formatMessage("Only players can run this!"));
            return Command.SINGLE_SUCCESS;
        }
        float speed = FloatArgumentType.getFloat(ctx, "speed percentage") * 0.01f;
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(0);
        String speedString = decimalFormat.format(speed*100.0f);
        player.setWalkSpeed(speed);
        player.sendMessage(formatMessage("Set walk speed to " + speedString + "%"));
        return Command.SINGLE_SUCCESS;
    }
}
