package dev.dylancode.melon.motd;

import dev.dylancode.melon.melon.Melon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MotdConfig {
    public static List<Component> messages = new ArrayList<>();

    public static void load() {
        messages.clear();
        if (!new File(Melon.plugin.getDataFolder(), "motd.yml").exists()) {
            Melon.plugin.saveResource("motd.yml", false);
        }
        File file = new File(Melon.plugin.getDataFolder(), "motd.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        List<String> rawMessages = yaml.getStringList("messages");
        for (String rawMessage : rawMessages) {
            messages.add(MiniMessage.miniMessage().deserialize(rawMessage));
        }
    }
}
