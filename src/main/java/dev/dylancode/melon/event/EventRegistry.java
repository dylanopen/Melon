package dev.dylancode.melon.event;

import dev.dylancode.melon.broadcastevent.ForwardBroadcast;
import dev.dylancode.melon.customname.CustomNameUpdateOnJoin;
import dev.dylancode.melon.melon.Melon;
import org.bukkit.event.Listener;

public class EventRegistry {
    public static void handle(Listener listener) {
        Melon.plugin.getServer().getPluginManager().registerEvents(listener, Melon.plugin);
    }

    public static void register() {
        handle(new MotdServerListPingListener());
        handle(new CustomNameUpdateOnJoin());
        handle(new ForwardBroadcast());
    }
}
