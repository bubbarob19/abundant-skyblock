package net.abundantmc.abundantskyblock.warp;

import mc.obliviate.inventory.Icon;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import net.abundantmc.abundantskyblock.common.gui.AbundantGui;
import net.abundantmc.abundantskyblock.common.gui.AbundantIcon;
import net.abundantmc.abundantskyblock.warp.entity.WarpEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.List;
import java.util.Optional;

public class WarpGui extends AbundantGui {
    public static final String ID = "warpGui";
    public static final String TITLE = "Warps";

    private static final Integer ROWS = 5;

    private final WarpService warpService;
    private final MessagingService messagingService;
    private final Player player;

    public WarpGui(WarpRepository warpRepository,
                   WarpService warpService,
                   MessagingService messagingService,
                   ComponentService componentService,
                   Player player) {
        super(player, ID, TITLE, ROWS, messagingService, componentService);
        this.warpService = warpService;
        this.messagingService = messagingService;
        this.player = player;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        messagingService.pop(player, 1);
        initialPopulation();

        List<WarpEntity> warps = warpService.findAllWarps()
                .stream()
                .filter(warpEntity -> player.hasPermission(warpEntity.permission()))
                .limit(21)
                .toList();

        List<Integer> slots = getFillerSlots();
        for (int i = 0; i < Math.min(21, warps.size()); i++) {
            WarpEntity warp = warps.get(i);
            addItem(
                    slots.get(i),
                    AbundantIcon.fromIcon(new Icon(warp.icon())
                            .onClick(inventoryClickEvent -> {
                                warpService.warpPlayer(warp, player);
                                getInventory().close();
                            }))
                            .setName(text("<gold>" +  warp.name()))
                            .setLore(
                                    loreList(
                                            "",
                                            "Location:",
                                            "  <dot> <gray>World: <white>" + warp.location().getWorld().getName(),
                                            "  <dot> <gray>X: <white>" + warp.location().getBlockX(),
                                            "  <dot> <gray>Y: <white>" + warp.location().getBlockY(),
                                            "  <dot> <gray>Z: <white>" + warp.location().getBlockZ(),
                                            "",
                                            "<dot> <gray>Click here to warp!"
                                    )
                            )
            );
        }
    }
}
