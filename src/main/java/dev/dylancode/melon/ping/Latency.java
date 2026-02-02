package dev.dylancode.melon.ping;

import org.bukkit.entity.Player;

public class Latency {
    public static int getMillis(Player player) {
        return player.getPing();
    }
}
