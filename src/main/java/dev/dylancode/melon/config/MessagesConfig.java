package dev.dylancode.melon.config;

import dev.dylancode.melon.melon.Melon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class MessagesConfig {
    public static TextColor primaryColor;
    public static TextColor secondaryColor;
    public static TextColor tertiaryColor;

    public static String confirmKick;
    public static String confirmMsg;
    public static String confirmFlyFallDamageEnable;
    public static String confirmFlyFallDamageDisable;
    public static String confirmGamemode;
    public static String confirmFlyspeed;
    public static String confirmWalkspeed;
    public static String confirmFlyEnable;
    public static String confirmFlyDisable;
    public static String confirmRenderdistance;
    public static String confirmHealth;
    public static String confirmMaxhealth;
    public static String confirmSetnick;
    public static String confirmSetprefix;
    public static String confirmSetsuffix;
    public static String confirmClear;

    public static String receiveKick;
    public static String receiveMsg;

    public static String queryIpaddress;
    public static String queryClientbrand;
    public static String queryRenderdistance;
    public static String queryUuid;
    public static String queryHealth;
    public static String queryMaxhealth;
    public static String queryGetnick;
    public static String queryGetprefix;
    public static String queryGetsuffix;
    public static String queryList;

    public static String listPlayer;

    public static Component formatMessage(String message) {
        message = "<" + secondaryColor.asHexString() + ">" + message;
        return miniMessage().deserialize(message);
    }

    public static String applyPlaceholders(String message, HashMap<String, String> placeholders) {
        placeholders.put("primary", primaryColor.asHexString());
        placeholders.put("secondary", secondaryColor.asHexString());
        placeholders.put("tertiary", tertiaryColor.asHexString());
        for (String key : placeholders.keySet()) {
            message = message.replace("%"+key+"%", placeholders.get(key));
        }
        return message;
    }

    public static void load() {
        if (!new File(Melon.plugin.getDataFolder(), "messages.yml").exists()) {
            Melon.plugin.saveResource("messages.yml", false);
        }
        File file = new File(Melon.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

        primaryColor = TextColor.fromHexString(Objects.requireNonNull(yaml.getString("color.primary")));
        secondaryColor = TextColor.fromHexString(Objects.requireNonNull(yaml.getString("color.secondary")));
        tertiaryColor = TextColor.fromHexString(Objects.requireNonNull(yaml.getString("color.tertiary")));
        confirmKick = yaml.getString("confirm.kick");
        confirmMsg = yaml.getString("confirm.msg");
        confirmFlyFallDamageEnable = yaml.getString("confirm.fly-fall-damage-enable");
        confirmFlyFallDamageDisable = yaml.getString("confirm.fly-fall-damage-disable");
        receiveKick = yaml.getString("receive.kick");
        receiveMsg = yaml.getString("receive.msg");
        confirmGamemode = yaml.getString("confirm.gamemode");
        confirmFlyspeed = yaml.getString("confirm.flyspeed");
        confirmWalkspeed = yaml.getString("confirm.walkspeed");
        confirmFlyEnable = yaml.getString("confirm.fly-enable");
        confirmFlyDisable = yaml.getString("confirm.fly-disable");
        confirmRenderdistance = yaml.getString("confirm.render-distance");
        confirmHealth = yaml.getString("confirm.health");
        confirmMaxhealth = yaml.getString("confirm.health");
        confirmSetnick = yaml.getString("confirm.setnick");
        confirmSetprefix = yaml.getString("confirm.setprefix");
        confirmSetsuffix = yaml.getString("confirm.setsuffix");
        confirmClear = yaml.getString("confirm.clear");
        queryIpaddress = yaml.getString("query.ip-address");
        queryClientbrand = yaml.getString("query.client-brand");
        queryRenderdistance = yaml.getString("query.render-distance");
        queryUuid = yaml.getString("query.uuid");
        queryHealth = yaml.getString("query.health");
        queryMaxhealth = yaml.getString("query.maxhealth");
        queryGetnick = yaml.getString("query.getnick");
        queryGetprefix = yaml.getString("query.getprefix");
        queryGetsuffix = yaml.getString("query.getsuffix");
        queryList = yaml.getString("query.list");
        listPlayer = yaml.getString("list-player");
    }
}
