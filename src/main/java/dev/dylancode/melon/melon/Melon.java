package dev.dylancode.melon.melon;

import dev.dylancode.melon.motd.MotdServerListPingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Melon extends JavaPlugin {
    public static Melon plugin;

    @Override
    public void onEnable() {
        Melon.plugin = this;
        MelonReload.registerCommand();
        MelonReload.reload();

        getServer().getPluginManager().registerEvents(new MotdServerListPingListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Melon");
    }
}
