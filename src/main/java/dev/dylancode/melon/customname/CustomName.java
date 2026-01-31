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
    public String prefix;
    public String nickname;
    public String suffix;

    public CustomName(String prefix, String nickname, String suffix) {
        this.prefix = prefix;
        this.nickname = nickname;
        this.suffix = suffix;
    }

    public CustomName(Component prefix, Component nickname, Component suffix) {
        this.prefix = miniMessage().serialize(prefix);
        this.nickname = miniMessage().serialize(nickname);
        this.suffix = miniMessage().serialize(suffix);
    }

    public CustomName(Map<String, Object> map) throws InvalidConfigurationException {
        try {
            prefix = (String) map.get("prefix");
            nickname = (String) map.get("nickname");
            suffix = (String) map.get("suffix");
        } catch (NullPointerException e) {
            Melon.plugin.getLogger().severe("Melon: failed to deserialize a CustomName (is customnames.yml correctly formatted?): " + e.getMessage());
            throw new InvalidConfigurationException(e);
        }
    }

    public void sendUpdate(Player player) {
        player.displayName(displayName());
        CustomNameStorage.save();
    }

    public String fullName() {
        return prefix + nickname + suffix;
    }

    public Component displayName() {
        return miniMessage().deserialize(fullName());
    }

    public Component deserializePrefix() {
        return miniMessage().deserialize(prefix);
    }

    public Component deserializeNickname() {
        return miniMessage().deserialize(nickname);
    }

    public Component deserializeSuffix() {
        return miniMessage().deserialize(suffix);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        return Map.of(
                "prefix", prefix,
                "nickname", nickname,
                "suffix", suffix
        );
    }
}
