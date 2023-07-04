package net.abundantmc.abundantskyblock.listener;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.common.AbundantLogger;
import net.abundantmc.abundantskyblock.playerdata.PlayerDataService;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import net.abundantmc.abundantskyblock.playerdata.exception.PlayerDataNotFoundException;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class QuitListener implements Listener {
    private final PlayerDataService playerDataService;
    private final ComponentService componentService;
    private final AbundantLogger logger;

    @Inject
    public QuitListener(PlayerDataService playerDataService, ComponentService componentService, AbundantLogger logger) {
        this.playerDataService = playerDataService;
        this.componentService = componentService;
        this.logger = logger;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        event.quitMessage(getQuitMessage(player));

        playerDataService.handlePlayerQuit(event.getPlayer().getUniqueId());
        logger.info("Saved data for player quit: " + uuid);
    }

    private Component getQuitMessage(Player player) {
        UUID uuid = player.getUniqueId();
        PlayerDataEntity playerData = playerDataService.findFromUUID(uuid).orElseThrow(() -> new PlayerDataNotFoundException(uuid));

        return componentService.getComponent(
                "<dark_gray>[<gold>-<dark_gray>] <gray>" + playerData.getNickname()
        );
    }
}
