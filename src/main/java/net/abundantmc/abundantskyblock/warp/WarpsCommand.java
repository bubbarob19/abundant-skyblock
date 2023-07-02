package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpsCommand implements CommandExecutor {
    private final WarpService warpService;
    private final ComponentService componentService;
    private final MessagingService messagingService;

    @Inject
    public WarpsCommand(WarpService warpService, ComponentService componentService, MessagingService messagingService) {
        this.warpService = warpService;
        this.componentService = componentService;
        this.messagingService = messagingService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {
        if (commandSender instanceof Player player) {
            messagingService.sendMsg(player, getWarpsMessage());
            messagingService.pling(player, 2);
        }

        return true;
    }

    private Component getWarpsMessage() {
        return componentService.getComponent(
                "",
                "<b><yellow>WARPS<!b>:<white>",
                getWarpsString(),
                ""
        );
    }

    private String getWarpsString() {
        return componentService.getCommaList(
                warpService.findAllWarps()
                        .stream()
                        .map(WarpEntity::name)
                        .toList()
        );
    }
}
