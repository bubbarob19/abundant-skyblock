package net.abundantmc.abundantskyblock.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import mc.obliviate.inventory.InventoryAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginModule extends AbstractModule {
    private final JavaPlugin plugin;

    public PluginModule(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(JavaPlugin.class).toInstance(plugin);
        bind(InventoryAPI.class).toInstance(new InventoryAPI(plugin));
    }
}
