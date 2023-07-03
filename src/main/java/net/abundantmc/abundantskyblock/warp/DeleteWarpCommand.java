package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class DeleteWarpCommand implements CommandExecutor {

    final MessagingService messagingService;
    final ComponentService componentService;
    final WarpService warpService;

    @Inject
    public DeleteWarpCommand(MessagingService messagingService, ComponentService componentService, WarpService warpService) {
        this.messagingService = messagingService;
        this.componentService = componentService;
        this.warpService = warpService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {
        if(arguments.length == 0) {
            messagingService.error(commandSender, "Please enter a warp to delete.");
            return true;
        }
        if(warpService.warpExists(arguments[0])) {
            warpService.deleteWarp(arguments[0]);
            messagingService.sendMsg(commandSender, componentService.getPrefix() + "Warp was successfully deleted.");
            return true;
        }
        messagingService.error(commandSender, "Pleaser enter a valid warp to delete.");
        return true;
    }
}
