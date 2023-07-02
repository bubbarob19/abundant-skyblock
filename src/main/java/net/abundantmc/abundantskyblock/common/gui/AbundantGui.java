package net.abundantmc.abundantskyblock.common.gui;

import mc.obliviate.inventory.Gui;
import mc.obliviate.inventory.Icon;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AbundantGui extends Gui {
    public static final Icon FILLER = new Icon(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    public static final Icon BORDER = new Icon(Material.BLACK_STAINED_GLASS_PANE);

    public AbundantGui(@NotNull Player player, @NotNull String id, String title, int rows) {
        super(player, id, title, rows);
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, String title, InventoryType inventoryType) {
        super(player, id, title, inventoryType);
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, Component title, int rows) {
        super(player, id, title, rows);
    }

    public AbundantGui(@NotNull Player player, @NotNull String id, Component title, InventoryType inventoryType) {
        super(player, id, title, inventoryType);
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
        for (int i = 0; i < size; i++) {
            if ((i <= 8)
                    || (i >= (size * 9) - 8) && (i <= (size * 9) - 2)
                    || i % 9 == 0
                    || i % 9 == 8)
                slots.add(i);
        }
        return slots;
    }

    public List<Integer> getFillerSlots() {
        List<Integer> slots = new ArrayList<>();
        int size = getSize();
        for (int i = 0; i < size; i++) {
            if (!((i <= 8)
                    || (i >= (size * 9) - 8) && (i <= (size * 9) - 2)
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
}
