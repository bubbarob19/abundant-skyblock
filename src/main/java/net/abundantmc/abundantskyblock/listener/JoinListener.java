package net.abundantmc.abundantskyblock.listener;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.playerdata.PlayerDataService;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import net.abundantmc.abundantskyblock.playerdata.exception.PlayerDataNotFoundException;
import net.abundantmc.abundantskyblock.warp.WarpService;
import net.abundantmc.abundantskyblock.warp.entity.WarpEntity;
import net.abundantmc.abundantskyblock.warp.exception.WarpNotFoundException;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {
    private static final String SPAWN = "spawn";

    private final PlayerDataService playerDataService;
    private final ComponentService componentService;
    private final WarpService warpService;

    @Inject
    public JoinListener(PlayerDataService playerDataService, ComponentService componentService, WarpService warpService) {
        this.playerDataService = playerDataService;
        this.componentService = componentService;
        this.warpService = warpService;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.joinMessage(getJoinMessage(player));
        sendPlayerToSpawn(player);
    }

    private void sendPlayerToSpawn(Player player) {
        WarpEntity warp = warpService.findFromName(SPAWN).orElseThrow(() -> new WarpNotFoundException(SPAWN));
        warpService.warpPlayerSilently(warp, player);
    }

    private Component getJoinMessage(Player player) {
        UUID uuid = player.getUniqueId();
        PlayerDataEntity playerData = playerDataService.findFromUUID(uuid).orElseThrow(() -> new PlayerDataNotFoundException(uuid));

        if (playerData.isFirstTime())
            return componentService.getComponent(
                    "<gold><b>FIRST JOIN! <gray><!b>Welcome <white>"
                            + player.getName()
                            + " <gray>to <yellow><b>ABUNDANT! <!b><dark_grey>[<white>#<b>"
                            + playerData.getJoinNumber()
                            + "<!b><dark_gray>]"
            );
        else
            return componentService.getComponent(
                    "<dark_gray>[<gold>+<dark_gray>] <gray>" + playerData.getNickname()
            );
    }

}
