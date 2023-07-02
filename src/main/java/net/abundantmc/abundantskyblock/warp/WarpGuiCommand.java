package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpGuiCommand implements CommandExecutor {
    private final WarpGuiFactory warpGuiFactory;

    @Inject
    public WarpGuiCommand(WarpGuiFactory warpGuiFactory) {
        this.warpGuiFactory = warpGuiFactory;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {
        if (commandSender instanceof Player player) {
            warpGuiFactory.create(player).open();
        }

        return true;
    }
}
