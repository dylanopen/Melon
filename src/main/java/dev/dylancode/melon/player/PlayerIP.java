package dev.dylancode.melon.player;

import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerIP {
    public static String get(Player player) {
        return Objects.requireNonNull(player.getAddress()).getHostName();
    }
}
