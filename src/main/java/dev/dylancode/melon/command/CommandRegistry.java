package dev.dylancode.melon.command;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void register(ReloadableRegistrarEvent<@NotNull Commands> commands) {
        LiteralCommandNode<CommandSourceStack> cmdFly = Commands.literal("fly")
                .requires(sender -> sender.getSender().hasPermission("melon.fly"))
                .executes(CmdFly::execute)
                .build();
        commands.registrar().register(cmdFly);

        LiteralCommandNode<CommandSourceStack> cmdFalldamage = Commands.literal("falldamage")
                .requires(sender -> sender.getSender().hasPermission("melon.falldamage"))
                .executes(CmdFalldamage::execute)
                .build();
        commands.registrar().register(cmdFalldamage);

        LiteralCommandNode<CommandSourceStack> cmdFlyspeed = Commands.literal("flyspeed")
                .requires(sender -> sender.getSender().hasPermission("melon.flyspeed"))
                .then(Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdFlyspeed.MIN_FLYSPEED, CmdFlyspeed.MAX_FLYSPEED))
                        .executes(CmdFlyspeed::execute))
                .build();
        commands.registrar().register(cmdFlyspeed);

        LiteralCommandNode<CommandSourceStack> cmdWalkspeed = Commands.literal("walkspeed")
                .requires(sender -> sender.getSender().hasPermission("melon.walkspeed"))
                .then(Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdWalkspeed.MIN_WALKSPEED, CmdWalkspeed.MAX_WALKSPEED))
                        .executes(CmdWalkspeed::execute))
                .build();
        commands.registrar().register(cmdWalkspeed);

        LiteralCommandNode<CommandSourceStack> cmdGms = Commands.literal("gms")
                .requires(sender -> sender.getSender().hasPermission("melon.gms"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.SURVIVAL))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.SURVIVAL)))
                .build();
        commands.registrar().register(cmdGms);

        LiteralCommandNode<CommandSourceStack> cmdGmc = Commands.literal("gmc")
                .requires(sender -> sender.getSender().hasPermission("melon.gmc"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.CREATIVE))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.CREATIVE)))
                .build();
        commands.registrar().register(cmdGmc);

        LiteralCommandNode<CommandSourceStack> cmdGma = Commands.literal("gma")
                .requires(sender -> sender.getSender().hasPermission("melon.gma"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.ADVENTURE))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.ADVENTURE)))
                .build();
        commands.registrar().register(cmdGma);

        LiteralCommandNode<CommandSourceStack> cmdGmp = Commands.literal("gmp")
                .requires(sender -> sender.getSender().hasPermission("melon.gmp"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.SPECTATOR))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.SPECTATOR)))
                .build();
        commands.registrar().register(cmdGmp);

        LiteralCommandNode<CommandSourceStack> cmdKick = Commands.literal("kick")
                .requires(sender -> sender.getSender().hasPermission("melon.kick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("reason", StringArgumentType.greedyString())
                                .executes(CmdKick::execute)))
                .build();
        commands.registrar().register(cmdKick);

        LiteralCommandNode<CommandSourceStack> cmdMsg = Commands.literal("msg")
                .requires(sender -> sender.getSender().hasPermission("melon.msg"))
                .then(Commands.argument("player", ArgumentTypes.players())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(CmdMsg::execute)))
                .build();
        commands.registrar().register(cmdMsg);

        LiteralCommandNode<CommandSourceStack> cmdClientbrand = Commands.literal("clientbrand")
                .requires(sender -> sender.getSender().hasPermission("melon.clientbrand"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdClientbrand::execute))
                .build();
        commands.registrar().register(cmdClientbrand);

        LiteralCommandNode<CommandSourceStack> cmdIpaddress = Commands.literal("ipaddress")
                .requires(sender -> sender.getSender().hasPermission("melon.ipaddress"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdIpaddress::execute))
                .build();
        commands.registrar().register(cmdIpaddress);

        LiteralCommandNode<CommandSourceStack> cmdRenderdistance = Commands.literal("renderdistance")
                .requires(sender -> sender.getSender().hasPermission("melon.renderdistance.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdRenderdistanceGet::execute)
                        .then(Commands.argument("distance", IntegerArgumentType.integer(2, 32))
                                .requires(sender -> sender.getSender().hasPermission("melon.renderdistance.set"))
                                .executes(CmdRenderdistanceSet::execute)))
                .build();
        commands.registrar().register(cmdRenderdistance);

        LiteralCommandNode<CommandSourceStack> cmdUuid = Commands.literal("uuid")
                .requires(sender -> sender.getSender().hasPermission("melon.uuid"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdUuid::execute))
                .build();
        commands.registrar().register(cmdUuid);

        LiteralCommandNode<CommandSourceStack> cmdSayas = Commands.literal("sayas")
                .requires(sender -> sender.getSender().hasPermission("melon.sayas"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(CmdSayas::execute)))
                .build();
        commands.registrar().register(cmdSayas);

        LiteralCommandNode<CommandSourceStack> cmdHealth = Commands.literal("health")
                .requires(sender -> sender.getSender().hasPermission("melon.health.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdHealthGet::execute)
                        .then(Commands.argument("hp", DoubleArgumentType.doubleArg(0.0f))
                                .requires(sender -> sender.getSender().hasPermission("melon.health.set"))
                                .executes(CmdHealthSet::execute)))
                .build();
        commands.registrar().register(cmdHealth);

        LiteralCommandNode<CommandSourceStack> cmdMaxhealth = Commands.literal("maxhealth")
                .requires(sender -> sender.getSender().hasPermission("melon.maxhealth.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdMaxhealthGet::execute)
                        .then(Commands.argument("hp", DoubleArgumentType.doubleArg(1.0f))
                                .requires(sender -> sender.getSender().hasPermission("melon.maxhealth.set"))
                                .executes(CmdMaxhealthSet::execute)))
                .build();
        commands.registrar().register(cmdMaxhealth);

        LiteralCommandNode<CommandSourceStack> cmdGetnick = Commands.literal("getnick")
                .requires(sender -> sender.getSender().hasPermission("melon.getnick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdGetnick::execute))
                .build();
        commands.registrar().register(cmdGetnick);

        LiteralCommandNode<CommandSourceStack> cmdSetnick = Commands.literal("setnick")
                .requires(sender -> sender.getSender().hasPermission("melon.setnick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("nickname", StringArgumentType.greedyString())
                                .executes(CmdSetnick::execute)))
                .build();
        commands.registrar().register(cmdSetnick);

    }
}
