package dev.dylancode.melon.melon;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.dylancode.melon.motd.MotdConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public class MelonReload {
    public static void registerCommand() {
        LiteralCommandNode<CommandSourceStack> cmdReload = Commands.literal("melonreload").executes(MelonReload::execute).build();

        Melon.plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register(cmdReload);
        });
    }

    public static void reload() {
        MotdConfig.load();
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        reload();
        return Command.SINGLE_SUCCESS;
    }
}
