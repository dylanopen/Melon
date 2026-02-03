package dev.dylancode.melon.health;

import org.bukkit.entity.Player;


public class PlayerHealth {
    public static int get(Player player) {
        return (int) Math.round(player.getHealth());
    }

    public static boolean set(Player player, int hp) {
        if (hp < 0) {
            hp = 0;
        }
        if (hp > PlayerMaxHealth.get(player)) {
            return false;
        }
        player.setHealth(hp);
        return true;
    }

    public static void damage(Player player, int damageHP) {
        PlayerHealth.heal(player, -damageHP);
    }

    public static void heal(Player player, int healHP) {
        int newHealth = PlayerHealth.get(player) + healHP;
        PlayerHealth.set(player, newHealth);
    }

    public static void heal(Player player) {
        int newHealth = PlayerMaxHealth.get(player);
        PlayerHealth.set(player, newHealth);
    }
}
