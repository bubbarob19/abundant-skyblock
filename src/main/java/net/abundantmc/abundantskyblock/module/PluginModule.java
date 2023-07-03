package net.abundantmc.abundantskyblock.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public class PluginModule extends AbstractModule {
    private final JavaPlugin plugin;

    public PluginModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(JavaPlugin.class).toInstance(plugin);
        bind(Server.class).toInstance(plugin.getServer());
        bind(BukkitScheduler.class).toInstance(plugin.getServer().getScheduler());
        bind(Logger.class).toInstance(plugin.getLogger());
        bind(InventoryAPI.class).toInstance(new InventoryAPI(plugin));
    }
}
