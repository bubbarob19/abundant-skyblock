package net.abundantmc.abundantskyblock.warp;

import mc.obliviate.inventory.Icon;
import net.abundantmc.abundantskyblock.common.gui.AbundantGui;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.List;

import static net.abundantmc.abundantskyblock.common.constant.TextConstant.DOT;

public class WarpGui extends AbundantGui {
    public static final String ID = "warpGui";
    public static final String TITLE = "Warps";

    private static final Integer ROWS = 5;

    private final WarpRepository warpRepository;
    private final WarpService warpService;
    private final Player player;

    public WarpGui(WarpRepository warpRepository, WarpService warpService, Player player) {
        super(player, ID, TITLE, ROWS);
        this.warpRepository = warpRepository;
        this.warpService = warpService;
        this.player = player;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        initialPopulation();

        List<WarpEntity> warps = warpRepository.findAll()
                .stream()
                .filter(warpEntity -> player.hasPermission(warpEntity.permission()))
                .limit(21)
                .toList();

        List<Integer> slots = getFillerSlots();
        for (int i = 0; i < Math.min(21, warps.size()); i++) {
            WarpEntity warp = warps.get(i);

            addItem(
                    slots.get(i),
                    new Icon(warp.icon())
                            .setName(warp.name())
                            .setLore(
                                    "",
                                    DOT + " <light_gray>Click here to warp!"
                            )
                            .onClick(inventoryClickEvent -> {
                                warpService.warpPlayer(warp, player);
                                getInventory().close();
                            })
            );
        }
    }
}
