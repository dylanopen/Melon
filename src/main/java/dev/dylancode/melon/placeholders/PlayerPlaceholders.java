package dev.dylancode.melon.placeholders;

import dev.dylancode.melon.customname.CustomNameStorage;
import dev.dylancode.melon.health.PlayerHealth;
import dev.dylancode.melon.health.PlayerMaxHealth;
import dev.dylancode.melon.player.PlayerIP;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class PlayerPlaceholders {
    public static HashMap<String, String> get(Player player, String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        UUID uuid = player.getUniqueId();
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put(prefix + "username", player.getName());
        placeholders.put(prefix + "uuid", uuid.toString());
        placeholders.put(prefix + "displayname", miniMessage().serialize(player.displayName()));
        placeholders.put(prefix + "health", String.valueOf(PlayerHealth.get(player)));
        placeholders.put(prefix + "maxhealth", String.valueOf(PlayerMaxHealth.get(player)));
        placeholders.put(prefix + "clientbrand", player.getClientBrandName());
        placeholders.put(prefix + "ipaddress", PlayerIP.get(player));
        placeholders.put(prefix + "prefix", CustomNameStorage.getPrefix(uuid));
        placeholders.put(prefix + "nick", CustomNameStorage.getNick(uuid));
        placeholders.put(prefix + "suffix", CustomNameStorage.getSuffix(uuid));
        placeholders.put(prefix + "world", player.getWorld().getName());
        placeholders.put(prefix + "location-x", String.valueOf((int) player.getLocation().x()));
        placeholders.put(prefix + "location-y", String.valueOf((int) player.getLocation().y()));
        placeholders.put(prefix + "location-z", String.valueOf((int) player.getLocation().z()));
        placeholders.put(prefix + "gamemode", player.getGameMode().name().toLowerCase());
        placeholders.put(prefix + "flyspeed", String.valueOf(player.getFlySpeed()));
        placeholders.put(prefix + "walkspeed", String.valueOf(player.getWalkSpeed()));
        placeholders.put(prefix + "renderdistance", String.valueOf(player.getClientViewDistance()));
        placeholders.put(prefix + "simulationdistance", String.valueOf(player.getSimulationDistance()));
        placeholders.put(prefix + "ping", String.valueOf(player.getPing()));
        return placeholders;
    }

    public static HashMap<String, String> get(Player player) {
        return get(player, "");
    }
}
