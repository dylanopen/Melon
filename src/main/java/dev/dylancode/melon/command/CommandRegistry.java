package dev.dylancode.melon.command;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void register(ReloadableRegistrarEvent<@NotNull Commands> commands) {
        LiteralCommandNode<CommandSourceStack> cmdFly = Commands.literal("mfly")
                .requires(sender -> sender.getSender().hasPermission("melon.fly"))
                .executes(CmdFly::execute).build();
        commands.registrar().register(cmdFly);

        LiteralCommandNode<CommandSourceStack> cmdFalldamage = Commands.literal("mfalldamage")
                .requires(sender -> sender.getSender().hasPermission("melon.falldamage"))
                .executes(CmdFalldamage::execute).build();
        commands.registrar().register(cmdFalldamage);

        LiteralCommandNode<CommandSourceStack> cmdFlyspeed = Commands.literal("mflyspeed").then(
                Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdFlyspeed.MIN_FLYSPEED, CmdFlyspeed.MAX_FLYSPEED))
                        .requires(sender -> sender.getSender().hasPermission("melon.flyspeed"))
                        .executes(CmdFlyspeed::execute)).build();
        commands.registrar().register(cmdFlyspeed);

        LiteralCommandNode<CommandSourceStack> cmdWalkspeed = Commands.literal("mwalkspeed").then(
                Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdWalkspeed.MIN_WALKSPEED, CmdWalkspeed.MAX_WALKSPEED))
                        .requires(sender -> sender.getSender().hasPermission("melon.walkspeed"))
                        .executes(CmdWalkspeed::execute)).build();
        commands.registrar().register(cmdWalkspeed);

    }
}
