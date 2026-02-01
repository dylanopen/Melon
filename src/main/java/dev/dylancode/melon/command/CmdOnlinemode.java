package dev.dylancode.melon.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import dev.dylancode.melon.config.MessagesConfig;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class CmdOnlinemode {
    public static int execute(CommandContext<CommandSourceStack> ctx) {
        boolean onlineMode = Bukkit.getOnlineMode();
        String messageTemplate;
        if (onlineMode) {
            messageTemplate = MessagesConfig.queryOnlinemodeTrue;
        } else {
            messageTemplate = MessagesConfig.queryOnlinemodeFalse;
        }
        HashMap<String, String> placeholders = getPlaceholders(ctx);
        Component response = formatMessage(applyPlaceholders(messageTemplate, placeholders));
        ctx.getSource().getSender().sendMessage(response);
        return Command.SINGLE_SUCCESS;
    }

    public static HashMap<String, String> getPlaceholders(CommandContext<CommandSourceStack> ctx) {
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put("sender", ctx.getSource().getSender().getName());
        return placeholders;
    }
}
