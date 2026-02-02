package dev.dylancode.melon.health;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PlayerMaxHealth {
    public static int get(Player player) {
        AttributeInstance attribute = Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH));
        return (int) Math.round(attribute.getValue());
    }

    public static void set(Player player, int maxHP) {
        if (maxHP < 1) {
            maxHP = 1;
        }
        Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH))
                .setBaseValue(maxHP);
    }

    public static void increase(Player player, int hpIncrease) {
        int newHealth = PlayerHealth.get(player)-hpIncrease;
        PlayerHealth.set(player, newHealth);
    }

    public static void decrease(Player player, int hpDecrease) {
        increase(player, -hpDecrease);
    }
}
