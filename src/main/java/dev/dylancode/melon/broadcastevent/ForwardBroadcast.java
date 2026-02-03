package dev.dylancode.melon.broadcastevent;

import dev.dylancode.melon.broadcast.BroadcastMessage;
import dev.dylancode.melon.config.BroadcastConfig;
import dev.dylancode.melon.placeholders.PlayerPlaceholders;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;

public class ForwardBroadcast implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        HashMap<String, String> placeholders = PlayerPlaceholders.get(player, "player-");
        Component message;
        if (player.hasPlayedBefore()) {
            message = formatMessage(applyPlaceholders(BroadcastConfig.playerJoin, placeholders));
        } else {
            message = formatMessage(applyPlaceholders(BroadcastConfig.playerJoinFirst, placeholders));
        }
        event.joinMessage(null);
        new BroadcastMessage(message);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        HashMap<String, String>placeholders = PlayerPlaceholders.get(player, "player-");
        Component message = formatMessage(applyPlaceholders(BroadcastConfig.playerQuit,placeholders));
        event.quitMessage(null);
        new BroadcastMessage(message);
    }
}
