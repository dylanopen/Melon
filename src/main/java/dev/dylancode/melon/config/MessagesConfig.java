package dev.dylancode.melon.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import java.util.HashMap;

public class MessagesConfig {
    public static TextColor melonColor = TextColor.fromHexString("#77cc44");
    public static TextColor messageColor = TextColor.fromHexString("#e79708");
    public static String kickMessage = "<%melon_color%>[Melon]<%message_color%> You have been kicked by <%melon_color%>%executor%\n\nReason:<%message_color%> %reason%";
    public static String msgReceiveMessage = "<%melon_color%>[Melon]<%message_color%> Message from <%melon_color%>%sender%:<%message_color%> %message%";

    public static Component formatMessage(String message) {
        return Component.text("[Melon] ", melonColor).append(Component.text(message, messageColor));
    }

    public static String applyPlaceholders(String message, HashMap<String, String> placeholders) {
        placeholders.put("melon_color", melonColor.asHexString());
        placeholders.put("message_color", messageColor.asHexString());
        for (String key : placeholders.keySet()) {
            message = message.replace("%"+key+"%", placeholders.get(key));
        }
        return message;
    }
}
