package dev.dylancode.melon.customname;

import dev.dylancode.melon.melon.Melon;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public class CustomNameStorage {
    public static HashMap<UUID, CustomName> customNames = new HashMap<>();

    public static String getPrefix(UUID uuid) {
        if (!customNames.containsKey(uuid)) {
            insertEmpty(uuid);
        }
        CustomName customName = customNames.get(uuid);
        return customName.prefix;
    }

    public static String getNick(UUID uuid) {
        if (!customNames.containsKey(uuid)) {
            insertEmpty(uuid);
        }
        CustomName customName = customNames.get(uuid);
        return customName.nickname;
    }

    public static String getSuffix(UUID uuid) {
        if (!customNames.containsKey(uuid)) {
            insertEmpty(uuid);
        }
        CustomName customName = customNames.get(uuid);
        return customName.suffix;
    }

    public static void setNick(UUID uuid, String nickname) {
        if (!customNames.containsKey(uuid)) {
            insertEmpty(uuid);
        }
        CustomName customName = customNames.get(uuid);
        customName.nickname = nickname;
        customNames.put(uuid, customName);
        customName.sendUpdate(Objects.requireNonNull(Bukkit.getPlayer(uuid)));
    }

    public static void setPrefix(UUID uuid, String prefix) {
        if (!customNames.containsKey(uuid)) {
            insertEmpty(uuid);
        }
        CustomName customName = customNames.get(uuid);
        customName.prefix = prefix;
        customNames.put(uuid, customName);
        customName.sendUpdate(Objects.requireNonNull(Bukkit.getPlayer(uuid)));
    }

    public static void insertEmpty(UUID uuid) {
        CustomName customName = new CustomName(
                Component.empty(),
                Objects.requireNonNull(Bukkit.getPlayer(uuid)).name(),
                Component.empty()
        );
        customNames.put(uuid, customName);
    }

    public static void load() {
        if (!new File(Melon.plugin.getDataFolder(), "customnames.yml").exists()) {
            Melon.plugin.saveResource("customnames.yml", false);
        }
        File file = new File(Melon.plugin.getDataFolder(), "customnames.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        for (String key : yaml.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                CustomName customName = (CustomName)yaml.get(key);
                customNames.put(uuid, customName);
            } catch (Exception e) {
                Melon.plugin.getLogger().warning("Failed to load customnames.yml: key = " + key + ": " + e);
            }
        }
    }

    public static void save() {
        File file = new File(Melon.plugin.getDataFolder(), "customnames.yml");
        YamlConfiguration yaml = new YamlConfiguration();

        for (UUID uuid : customNames.keySet()) {
            yaml.set(uuid.toString(), customNames.get(uuid));
        }

        try {
            yaml.save(file);
        } catch (Exception e) {
            Melon.plugin.getLogger().severe("Failed to save customnames.yml: " + e);
        }
    }
}
