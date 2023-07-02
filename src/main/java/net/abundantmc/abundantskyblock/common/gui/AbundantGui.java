package net.abundantmc.abundantskyblock.common.gui;

import com.google.inject.Inject;
import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbundantGui extends Gui {
    public static final Icon FILLER = new Icon(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    public static final Icon BORDER = new Icon(Material.BLACK_STAINED_GLASS_PANE);

    private final MessagingService messagingService;
    private final ComponentService componentService;

    public AbundantGui(@NotNull Player player, @NotNull String id, String title, int rows, MessagingService messagingService, ComponentService componentService) {
        super(player, id, title, rows);
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, String title, InventoryType inventoryType, MessagingService messagingService, ComponentService componentService) {
        super(player, id, title, inventoryType);
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, Component title, int rows, MessagingService messagingService, ComponentService componentService) {
        super(player, id, title, rows);
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, Component title, InventoryType inventoryType, MessagingService messagingService, ComponentService componentService) {
        super(player, id, title, inventoryType);
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    public void fillBorder() {
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (getBorderSlots().contains(i))
                addItem(i, BORDER);
        }
    }

    public List<Integer> getBorderSlots() {
        List<Integer> slots = new ArrayList<>();
        int size = getSize();
        int rows = size / 9;
        for (int i = 0; i < size; i++) {
            if ((i <= 8)
                    || (i >= (rows * 9) - 8) && (i <= (rows * 9) - 2)
                    || i % 9 == 0
                    || i % 9 == 8)
                slots.add(i);
        }
        return slots;
    }

    public List<Integer> getFillerSlots() {
        List<Integer> slots = new ArrayList<>();
        int size = getSize();
        int rows = size / 9;
        for (int i = 0; i < size; i++) {
            if (!((i <= 8)
                    || (i >= (rows * 9) - 8) && (i <= (rows * 9) - 2)
                    || i % 9 == 0
                    || i % 9 == 8))
                slots.add(i);
        }
        return slots;
    }

    public void initialPopulation() {
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (getBorderSlots().contains(i))
                addItem(i, BORDER);
            else
                addItem(i, FILLER);
        }
    }

    public Component text(String... messages) {
        return componentService.getBlankComponent(messages);
    }

    public Component guiTitle(String message) { return componentService.getBlankComponent("<color:#373737>" + message); }

    public void playGuiSound(Audience audience) {
        messagingService.playSound(audience, Sound.ENTITY_CHICKEN_EGG);
    }

    public List<Component> loreList(String... messages) {
        return componentService.getBlankComponentList(messages);
    }

    public List<Component> loreList(int splitAmount, String... messages) {
        return componentService.getLoreLines(splitAmount, messages);
    }
}
