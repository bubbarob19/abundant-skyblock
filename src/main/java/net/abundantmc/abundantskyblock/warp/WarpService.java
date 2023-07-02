package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.entity.Player;

@Singleton
public class WarpService {
    private final MessagingService messagingService;
    private final ComponentService componentService;

    @Inject
    public WarpService(MessagingService messagingService, ComponentService componentService) {
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public void warpPlayer(WarpEntity warpEntity, Player player) {
        teleportPlayerToWarp(warpEntity, player);
        sendSuccessMessage(warpEntity, player);
    }

    private void teleportPlayerToWarp(WarpEntity warpEntity, Player player) {
        player.teleport(warpEntity.location());
    }

    private void sendSuccessMessage(WarpEntity warpEntity, Player player) {
        messagingService.sendMsg(player,
                componentService.getPrefix() + "You were warped to the warp: <orange>" + warpEntity.name()
        );
        messagingService.pling(player, 2);
    }
}
