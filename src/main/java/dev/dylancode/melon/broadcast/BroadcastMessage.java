package dev.dylancode.melon.broadcast;

import dev.dylancode.melon.melon.Melon;
import net.kyori.adventure.text.Component;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class BroadcastMessage {
    public BroadcastMessage(Component message) {
        Melon.plugin.getServer().sendMessage(message);
    }

    /// `message` is in minimessage format
    public BroadcastMessage(String message) {
        this(miniMessage().deserialize(message));
    }
}
