package net.abundantmc.abundantskyblock;

import com.google.inject.Guice;
import com.google.inject.Injector;
import mc.obliviate.inventory.InventoryAPI;
import net.abundantmc.abundantskyblock.module.ConfigModule;
import net.abundantmc.abundantskyblock.module.DatabaseModule;
import net.abundantmc.abundantskyblock.module.PluginModule;
import net.abundantmc.abundantskyblock.warp.WarpCommand;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbundantSkyblock extends JavaPlugin {
    private Injector injector;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(
                new ConfigModule(),
                new DatabaseModule(),
                new PluginModule(this)
        );
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        initializeInventoryApi();
        initializeCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void initializeInventoryApi() {
        InventoryAPI inventoryAPI = injector.getInstance(InventoryAPI.class);
        inventoryAPI.init();
    }

    private void initializeCommands() {
        this.getCommand("gamemode").setExecutor(injector.getInstance(WarpCommand.class));
    }
}
