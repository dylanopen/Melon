package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.simulationdistance.SimulationDistance;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdSimulationdistanceSet {
    public static int execute(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        CommandSender sender = ctx.getSource().getSender();
        final World world = ctx.getArgument("world", World.class);
        final int distance = ctx.getArgument("distance", Integer.class);
        SimulationDistance.set(world, distance);
        HashMap<String, String> placeholders = getPlaceholders(ctx, world, distance);
        sender.sendMessage(formatMessage(applyPlaceholders(MessagesConfig.confirmSimulationdistance, placeholders)));
        return Command.SINGLE_SUCCESS;
    }

    private static @NotNull HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx, World world, int distance) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        placeholders.put("world", world.getName());
        placeholders.put("distance", String.valueOf(distance));
        return placeholders;
    }
}
