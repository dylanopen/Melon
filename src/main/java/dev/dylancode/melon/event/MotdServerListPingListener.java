package dev.dylancode.melon.event;

import dev.dylancode.melon.config.MotdConfig;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

public class MotdServerListPingListener implements Listener {
    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        Random rand = new Random();
        Component message = MotdConfig.messages.get(rand.nextInt(MotdConfig.messages.size()));
        event.motd(message);
    }
}
