package dev.dylancode.melon.config;

import dev.dylancode.melon.customname.CustomName;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public class ConfigTypeRegistry {
    public static void init() {
        ConfigurationSerialization.registerClass(CustomName.class);
    }
}
