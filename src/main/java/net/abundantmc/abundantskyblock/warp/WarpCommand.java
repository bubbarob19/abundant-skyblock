package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WarpCommand implements CommandExecutor {

    private final WarpService warpService;
    private final MessagingService messagingService;

    @Inject
    public WarpCommand(WarpService warpService, MessagingService messagingService) {
        this.warpService = warpService;
        this.messagingService = messagingService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {
        // Implement here
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (arguments.length == 0) {
                messagingService.error(player, "Please provide a warp name.");
                return true;
            }
            warpService.findFromName(arguments[0]).ifPresentOrElse(
                        warpEntity
                                -> {
                            warpService.warpPlayer(warpEntity, player);
                        },
                        ()
                                -> {
                            messagingService.error(player, "This warp is invalid.");
                        });
        }
        return true;
    }
}
