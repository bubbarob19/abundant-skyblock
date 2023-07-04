package net.abundantmc.abundantskyblock.listener;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.common.AbundantLogger;
import net.abundantmc.abundantskyblock.playerdata.PlayerDataService;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import net.abundantmc.abundantskyblock.playerdata.exception.PlayerDataNotFoundException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class AsyncPlayerPreLoginListener implements Listener {
    private final PlayerDataService playerDataService;
    private final AbundantLogger logger;

    @Inject
    public AsyncPlayerPreLoginListener(PlayerDataService playerDataService, AbundantLogger logger) {
        this.playerDataService = playerDataService;
        this.logger = logger;
    }

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getPlayerProfile().getId();

        int joinNumber = playerDataService.getTotalPlayerCount() + 1;
        playerDataService.handlePlayerJoin(uuid);
        PlayerDataEntity playerData = playerDataService.findFromUUID(uuid).orElseThrow(() -> new PlayerDataNotFoundException(uuid));
        playerData.setJoinNumber(joinNumber);
        playerData.setFirstTime(true);
        logger.info("Loaded data for player join: " + uuid);
    }
}
