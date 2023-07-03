package net.abundantmc.abundantskyblock.playerdata.listener;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.playerdata.PlayerDataService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;
import java.util.logging.Logger;

public class PlayerDataListener implements Listener {
    private final PlayerDataService playerDataService;
    private final Logger logger;

    @Inject
    public PlayerDataListener(PlayerDataService playerDataService, Logger logger) {
        this.playerDataService = playerDataService;
        this.logger = logger;
    }

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getPlayerProfile().getId();
        playerDataService.handlePlayerJoin(uuid);
        logger.info("Loaded data for player join: " + uuid);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();
        playerDataService.handlePlayerQuit(event.getPlayer().getUniqueId());
        logger.info("Saved data for player quit: " + uuid);
    }
}
