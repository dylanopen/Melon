package dev.dylancode.melon.melon;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import dev.dylancode.melon.config.MessagesConfig;
import dev.dylancode.melon.config.MotdConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class MelonReload {
    public static void registerCommand() {
        LiteralCommandNode<CommandSourceStack> cmdReload = Commands.literal("melonreload")
                .requires(ctx -> ctx.getSender().hasPermission("melon.reload"))
                .executes(MelonReload::execute).build();

        Melon.plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS,
                commands -> commands.registrar().register(cmdReload));
    }

    public static void reload() {
        MotdConfig.load();
        MessagesConfig.load();
    }

    private static int execute(CommandContext<CommandSourceStack> ctx) {
        reload();
        ctx.getSource().getSender().sendMessage(formatMessage("Successfully reloaded all Melon configuration files"));
        return Command.SINGLE_SUCCESS;
    }
}
