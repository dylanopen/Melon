package dev.dylancode.melon.customname;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CustomNameUpdateOnJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (CustomNameStorage.customNames.containsKey(player.getUniqueId())) {
            CustomName customName = CustomNameStorage.customNames.get(player.getUniqueId());
            customName.sendUpdate(player);
        }
    }
}
