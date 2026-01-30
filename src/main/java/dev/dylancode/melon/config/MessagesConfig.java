package dev.dylancode.melon.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MessagesConfig {
    public static TextColor melonColor = TextColor.fromHexString("#77cc44");
    public static TextColor messageColor = TextColor.fromHexString("#e79708");

    public static Component formatMessage(String message) {
        return Component.text("[Melon] ", melonColor).append(Component.text(message, messageColor));
    }
}
