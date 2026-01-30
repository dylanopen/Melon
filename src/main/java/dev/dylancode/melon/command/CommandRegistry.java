package dev.dylancode.melon.command;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void register(ReloadableRegistrarEvent<@NotNull Commands> commands) {
        LiteralCommandNode<CommandSourceStack> cmdFlyspeed = Commands.literal("mflyspeed").then(
                Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdFlyspeed.MIN_FLYSPEED, CmdFlyspeed.MAX_FLYSPEED))
                        .executes(CmdFlyspeed::execute)).build();
        commands.registrar().register(cmdFlyspeed);

        LiteralCommandNode<CommandSourceStack> cmdWalkspeed = Commands.literal("mwalkspeed").then(
                Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdWalkspeed.MIN_WALKSPEED, CmdWalkspeed.MAX_WALKSPEED))
                        .executes(CmdWalkspeed::execute)).build();
        commands.registrar().register(cmdWalkspeed);

    }
}
