package net.abundantmc.abundantskyblock;

import com.google.inject.Guice;
import com.google.inject.Injector;
import mc.obliviate.inventory.InventoryAPI;
import net.abundantmc.abundantskyblock.module.ConfigModule;
import net.abundantmc.abundantskyblock.module.DatabaseModule;
import net.abundantmc.abundantskyblock.module.PluginModule;
import net.abundantmc.abundantskyblock.playerdata.PlayerDataSaveRunnable;
import net.abundantmc.abundantskyblock.playerdata.listener.PlayerDataListener;
import net.abundantmc.abundantskyblock.utilities.GamemodeCommand;
import net.abundantmc.abundantskyblock.warp.WarpGuiCommand;
import net.abundantmc.abundantskyblock.warp.WarpsCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbundantSkyblock extends JavaPlugin {
    private static final int TICKS_PER_SECOND = 20;
    private static final int SECONDS_PER_SAVE = 600;

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
        initializeListeners();
        scheduleSavingTask();
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
        this.getCommand("gamemode").setExecutor(injector.getInstance(GamemodeCommand.class));
        this.getCommand("warpgui").setExecutor(injector.getInstance(WarpGuiCommand.class));
        this.getCommand("warps").setExecutor(injector.getInstance(WarpsCommand.class));
    }

    private void initializeListeners() {
        PluginManager manager = this.getServer().getPluginManager();

        manager.registerEvents(injector.getInstance(PlayerDataListener.class), this);
    }

    private void scheduleSavingTask() {
        injector.getInstance(PlayerDataSaveRunnable.class).runTaskTimer(this, TICKS_PER_SECOND * 60, TICKS_PER_SECOND * SECONDS_PER_SAVE);
    }
}
