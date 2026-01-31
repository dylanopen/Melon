package dev.dylancode.melon.customname;

import dev.dylancode.melon.melon.Melon;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class CustomName implements ConfigurationSerializable {
    public Component prefix;
    public Component nickname;
    public Component suffix;

    public CustomName(Component prefix, Component nickname, Component suffix) {
        this.prefix = prefix;
        this.nickname = nickname;
        this.suffix = suffix;
    }

    public CustomName(Map<String, Object> map) throws InvalidConfigurationException {
        try {
            prefix = miniMessage().deserialize((String) map.get("prefix"));
            nickname = miniMessage().deserialize((String) map.get("nickname"));
            suffix = miniMessage().deserialize((String) map.get("suffix"));
        } catch (NullPointerException e) {
            Melon.plugin.getLogger().severe("Melon: failed to deserialize a CustomName (is customnames.yml correctly formatted?): " + e.getMessage());
            throw new InvalidConfigurationException(e);
        }
    }

    public void sendUpdate(Player player) {
        player.displayName(displayName());
        CustomNameStorage.save();
    }

    public Component displayName() {
        return Component.empty()
                .append(prefix)
                .append(nickname)
                .append(suffix);
    }

    public String serializePrefix() {
        return miniMessage().serialize(prefix);
    }

    public String serializeNickname() {
        return miniMessage().serialize(nickname);
    }

    public String serializeSuffix() {
        return miniMessage().serialize(suffix);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return Map.of(
                "prefix", serializePrefix(),
                "nickname", serializeNickname(),
                "suffix", serializeSuffix()
        );
    }
}
