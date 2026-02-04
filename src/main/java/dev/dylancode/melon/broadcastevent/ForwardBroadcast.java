package dev.dylancode.melon.broadcastevent;

import dev.dylancode.melon.broadcast.BroadcastMessage;
import dev.dylancode.melon.config.BroadcastConfig;
import dev.dylancode.melon.placeholders.BlockPlaceholders;
import dev.dylancode.melon.placeholders.PlayerPlaceholders;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

import static dev.dylancode.melon.config.MessagesConfig.applyPlaceholders;
import static dev.dylancode.melon.config.MessagesConfig.formatMessage;
import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class ForwardBroadcast implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (BroadcastConfig.playerJoin.isEmpty()) {
            return;
        }
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
        if (!BroadcastConfig.playerQuit.isEmpty()) {
            return;
        }
        Player player = event.getPlayer();
        HashMap<String, String>placeholders = PlayerPlaceholders.get(player, "player-");
        Component message = formatMessage(applyPlaceholders(BroadcastConfig.playerQuit,placeholders));
        event.quitMessage(null);
        new BroadcastMessage(message);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.deathMessage() == null) {
            return;
        }
        if (BroadcastConfig.playerDeath.isEmpty()) {
            return;
        }
        String deathMessage = miniMessage().serialize(event.deathMessage());
        HashMap<String, String> placeholders = PlayerPlaceholders.get(event.getPlayer(), "player-");
        placeholders.put("death-message", deathMessage);
        Component message = formatMessage(applyPlaceholders(BroadcastConfig.playerDeath, placeholders));
        event.deathMessage(null);
        new BroadcastMessage(message);
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        if (BroadcastConfig.playerBucketFill.isEmpty()) {
            return;
        }
        Block block = event.getBlock();
        Material bucketMaterial = event.getBucket();
        Player player = event.getPlayer();
        HashMap<String, String> placeholders = PlayerPlaceholders.get(player, "player-");
        placeholders.putAll(BlockPlaceholders.get(block, "block-"));
        placeholders.put("bucket-material", bucketMaterial.toString().toLowerCase());

        Component message = formatMessage(applyPlaceholders(BroadcastConfig.playerBucketFill,placeholders));
        new BroadcastMessage(message);
    }
}
