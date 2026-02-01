package dev.dylancode.melon.command;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import org.bukkit.GameMode;
import org.jetbrains.annotations.NotNull;

public class CommandRegistry {
    public static void register(ReloadableRegistrarEvent<@NotNull Commands> commands) {
        var r = commands.registrar();
        r.register(Commands.literal("fly")
                .requires(sender -> sender.getSender().hasPermission("melon.fly"))
                .executes(CmdFly::execute)
                .build());

        r.register(Commands.literal("falldamage")
                .requires(sender -> sender.getSender().hasPermission("melon.falldamage"))
                .executes(CmdFalldamage::execute)
                .build());

        r.register(Commands.literal("flyspeed")
                .requires(sender -> sender.getSender().hasPermission("melon.flyspeed"))
                .then(Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdFlyspeed.MIN_FLYSPEED, CmdFlyspeed.MAX_FLYSPEED))
                        .executes(CmdFlyspeed::execute))
                .build());

        r.register(Commands.literal("walkspeed")
                .requires(sender -> sender.getSender().hasPermission("melon.walkspeed"))
                .then(Commands.argument("speed percentage", FloatArgumentType.floatArg(CmdWalkspeed.MIN_WALKSPEED, CmdWalkspeed.MAX_WALKSPEED))
                        .executes(CmdWalkspeed::execute))
                .build());

        r.register(Commands.literal("gms")
                .requires(sender -> sender.getSender().hasPermission("melon.gms"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.SURVIVAL))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.SURVIVAL)))
                .build());

        r.register(Commands.literal("gmc")
                .requires(sender -> sender.getSender().hasPermission("melon.gmc"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.CREATIVE))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.CREATIVE)))
                .build());

        r.register(Commands.literal("gma")
                .requires(sender -> sender.getSender().hasPermission("melon.gma"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.ADVENTURE))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.ADVENTURE)))
                .build());

        r.register(Commands.literal("gmp")
                .requires(sender -> sender.getSender().hasPermission("melon.gmp"))
                .executes(ctx -> CmdGm.executeSelf(ctx, GameMode.SPECTATOR))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(ctx -> CmdGm.executeOthers(ctx, GameMode.SPECTATOR)))
                .build());

        r.register(Commands.literal("kick")
                .requires(sender -> sender.getSender().hasPermission("melon.kick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("reason", StringArgumentType.greedyString())
                                .executes(CmdKick::execute)))
                .build());

        r.register(Commands.literal("msg")
                .requires(sender -> sender.getSender().hasPermission("melon.msg"))
                .then(Commands.argument("player", ArgumentTypes.players())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(CmdMsg::execute)))
                .build());

        r.register(Commands.literal("clientbrand")
                .requires(sender -> sender.getSender().hasPermission("melon.clientbrand"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdClientbrand::execute))
                .build());

        r.register(Commands.literal("ipaddress")
                .requires(sender -> sender.getSender().hasPermission("melon.ipaddress"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdIpaddress::execute))
                .build());

        r.register(Commands.literal("renderdistance")
                .requires(sender -> sender.getSender().hasPermission("melon.renderdistance.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdRenderdistanceGet::execute)
                        .then(Commands.argument("distance", IntegerArgumentType.integer(2, 32))
                                .requires(sender -> sender.getSender().hasPermission("melon.renderdistance.set"))
                                .executes(CmdRenderdistanceSet::execute)))
                .build());

        r.register(Commands.literal("uuid")
                .requires(sender -> sender.getSender().hasPermission("melon.uuid"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdUuid::execute))
                .build());

        r.register(Commands.literal("sayas")
                .requires(sender -> sender.getSender().hasPermission("melon.sayas"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("message", StringArgumentType.greedyString())
                                .executes(CmdSayas::execute)))
                .build());

        r.register(Commands.literal("health")
                .requires(sender -> sender.getSender().hasPermission("melon.health.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdHealthGet::execute)
                        .then(Commands.argument("hp", DoubleArgumentType.doubleArg(0.0f))
                                .requires(sender -> sender.getSender().hasPermission("melon.health.set"))
                                .executes(CmdHealthSet::execute)))
                .build());

        r.register(Commands.literal("maxhealth")
                .requires(sender -> sender.getSender().hasPermission("melon.maxhealth.get"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdMaxhealthGet::execute)
                        .then(Commands.argument("hp", DoubleArgumentType.doubleArg(1.0f))
                                .requires(sender -> sender.getSender().hasPermission("melon.maxhealth.set"))
                                .executes(CmdMaxhealthSet::execute)))
                .build());

        r.register(Commands.literal("getnick")
                .requires(sender -> sender.getSender().hasPermission("melon.getnick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdGetnick::execute))
                .build());

        r.register(Commands.literal("setnick")
                .requires(sender -> sender.getSender().hasPermission("melon.setnick"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("nickname", StringArgumentType.string())
                                .executes(CmdSetnick::execute)))
                .build());

        r.register(Commands.literal("getprefix")
                .requires(sender -> sender.getSender().hasPermission("melon.getprefix"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdGetprefix::execute))
                .build());

        r.register(Commands.literal("setprefix")
                .requires(sender -> sender.getSender().hasPermission("melon.setprefix"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("prefix", StringArgumentType.string())
                                .executes(CmdSetprefix::execute)))
                .build());

        r.register(Commands.literal("getsuffix")
                .requires(sender -> sender.getSender().hasPermission("melon.getsuffix"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdGetsuffix::execute))
                .build());


        r.register(Commands.literal("setsuffix")
                .requires(sender -> sender.getSender().hasPermission("melon.setsuffix"))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("suffix", StringArgumentType.string())
                                .executes(CmdSetsuffix::execute)))
                .build());

        r.register(Commands.literal("clear")
                .requires(sender -> sender.getSender().hasPermission("melon.clear"))
                .executes(CmdClear::executeSelf)
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdClear::execute))
                .build());

        r.register(Commands.literal("clearslot")
                .requires(sender -> sender.getSender().hasPermission("melon.clearslot"))
                .then(Commands.argument("slot", IntegerArgumentType.integer(0, 44))
                        .executes(CmdClearslot::executeSelf))
                .then(Commands.argument("players", ArgumentTypes.players())
                        .then(Commands.argument("slot", IntegerArgumentType.integer(0, 44))
                                .executes(CmdClearslot::execute)))
                        .build());

        r.register(Commands.literal("clearhotbar")
                .requires(sender -> sender.getSender().hasPermission("melon.clearhotbar"))
                .executes(CmdClearhotbar::executeSelf)
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdClearhotbar::execute))
                .build());

        r.register(Commands.literal("cleararmor")
                .requires(sender -> sender.getSender().hasPermission("melon.cleararmor"))
                .executes(CmdCleararmor::executeSelf)
                .then(Commands.argument("players", ArgumentTypes.players())
                        .executes(CmdCleararmor::execute))
                .build());

    }
}
