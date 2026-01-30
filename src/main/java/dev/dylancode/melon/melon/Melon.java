package dev.dylancode.melon.melon;

import dev.dylancode.melon.command.CommandRegistry;
import dev.dylancode.melon.event.EventRegistry;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class Melon extends JavaPlugin {
    public static Melon plugin;

    @Override
    public void onEnable() {
        Melon.plugin = this;
        MelonReload.registerCommand();
        MelonReload.reload();

        EventRegistry.register();
        getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, CommandRegistry::register);

    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling Melon");
    }
}
