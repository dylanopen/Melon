package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class CmdFlyspeed {
    public static final float MIN_FLYSPEED = -100.0f;
    public static final float MAX_FLYSPEED = 100.0f;

    public static int execute(CommandContext<CommandSourceStack> ctx) {
        if (!(ctx.getSource().getExecutor() instanceof Player player)) {
            ctx.getSource().getSender().sendMessage(Component.text("[Melon] you must be a player to run this command!", NamedTextColor.RED));
            return Command.SINGLE_SUCCESS;
        }
        float flySpeed = FloatArgumentType.getFloat(ctx, "speed percentage") * 0.01f;
        player.setFlySpeed(flySpeed);
        return Command.SINGLE_SUCCESS;
    }
}
