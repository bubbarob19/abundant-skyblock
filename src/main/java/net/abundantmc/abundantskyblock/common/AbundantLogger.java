package net.abundantmc.abundantskyblock.common;

import com.google.inject.Inject;
import org.bukkit.plugin.Plugin;

public class AbundantLogger {
    private final Plugin plugin;

    @Inject
    public AbundantLogger(Plugin plugin) {
        this.plugin = plugin;
    }

    public void info(String message) {
        this.plugin.getLogger().info(message);
    }

    public void warn(String message) {
        this.plugin.getLogger().warning(message);
    }

    public void error(String message) {
        this.plugin.getLogger().severe(message);
    }
}
