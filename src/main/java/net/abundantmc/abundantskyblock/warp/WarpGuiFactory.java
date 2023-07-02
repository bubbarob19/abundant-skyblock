package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.entity.Player;

public class WarpGuiFactory {
    private final WarpRepository warpRepository;
    private final WarpService warpService;
    private final MessagingService messagingService;
    private final ComponentService componentService;

    @Inject
    public WarpGuiFactory(WarpRepository warpRepository, WarpService warpService, MessagingService messagingService, ComponentService componentService) {
        this.warpRepository = warpRepository;
        this.warpService = warpService;
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public WarpGui create(Player player) {
        return new WarpGui(
                warpRepository,
                warpService,
                messagingService,
                componentService,
                player
        );
    }
}
