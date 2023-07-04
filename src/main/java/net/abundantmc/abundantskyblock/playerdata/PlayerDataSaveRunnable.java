package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.common.AbundantLogger;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;
import java.util.logging.Logger;

public class PlayerDataSaveRunnable extends BukkitRunnable {
    private final PlayerDataService playerDataService;
    private final Plugin plugin;
    private final BukkitScheduler bukkitScheduler;
    private final AbundantLogger logger;

    @Inject
    public PlayerDataSaveRunnable(PlayerDataService playerDataService, Plugin plugin, BukkitScheduler bukkitScheduler, AbundantLogger logger) {
        this.playerDataService = playerDataService;
        this.plugin = plugin;
        this.bukkitScheduler = bukkitScheduler;
        this.logger = logger;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        List<PlayerDataEntity> onlinePlayers = playerDataService.allOnlinePlayerData();
        for (int i = 0; i < onlinePlayers.size(); i++) {
            bukkitScheduler.scheduleSyncDelayedTask(plugin, new SaveRunnable(startTime, onlinePlayers.get(i)), i * 20L);
        }
        logger.info("Scheduled Save Task for " + onlinePlayers.size() + " players");
    }

    private class SaveRunnable implements Runnable {
        private final long startTime;
        private final PlayerDataEntity entity;

        public SaveRunnable(long startTime, PlayerDataEntity entity) {
            this.startTime = startTime;
            this.entity = entity;
        }

        @Override
        public void run() {
            playerDataService.whenCached(entity.getId()).ifPresent(timeCached -> {
                if (startTime > timeCached)
                    return;

                playerDataService.save(entity);
            });
        }
    }
}
