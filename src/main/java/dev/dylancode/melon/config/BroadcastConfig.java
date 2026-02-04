package dev.dylancode.melon.config;

import dev.dylancode.melon.melon.Melon;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class BroadcastConfig {
    public static String playerJoin;
    public static String playerQuit;
    public static String playerJoinFirst;
    public static String playerDeath;
    public static String playerBucketFill;

    public static void load() {
        if (!new File(Melon.plugin.getDataFolder(), "broadcasts.yml").exists()) {
            Melon.plugin.saveResource("broadcasts.yml", false);
        }
        File file = new File(Melon.plugin.getDataFolder(), "broadcasts.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        playerJoin = yaml.getString("player-join");
        playerQuit = yaml.getString("player-quit");
        playerJoinFirst = yaml.getString("player-join-first");
        playerDeath = yaml.getString("player-death");
        playerBucketFill = yaml.getString("player-bucket-fill");
    }
}
