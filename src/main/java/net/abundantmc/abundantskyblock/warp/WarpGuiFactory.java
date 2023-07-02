package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import org.bukkit.entity.Player;

public class WarpGuiFactory {
    private final WarpRepository warpRepository;
    private final WarpService warpService;

    @Inject
    public WarpGuiFactory(WarpRepository warpRepository, WarpService warpService) {
        this.warpRepository = warpRepository;
        this.warpService = warpService;
    }

    public WarpGui create(Player player) {
        return new WarpGui(
                warpRepository,
                warpService,
                player
        );
    }
}
