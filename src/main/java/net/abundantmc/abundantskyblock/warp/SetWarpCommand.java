package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetWarpCommand implements CommandExecutor {

    final ComponentService componentService;
    final WarpService warpService;
    final MessagingService messagingService;

    @Inject
    public SetWarpCommand(ComponentService componentService, WarpService warpService, MessagingService messagingService) {
        this.componentService = componentService;
        this.warpService = warpService;
        this.messagingService = messagingService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments){
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(arguments.length == 0) {
                messagingService.error(player, "Please enter a warp name.");
                return true;
            }
            if(arguments.length == 1) {
                if(warpService.warpExists(arguments[0])) {
                    warpService.deleteWarp(arguments[0]);
                    messagingService.sendMsg(player, componentService.getPrefix() + "The warp was successfully changed.");
                }
                else {
                    messagingService.sendMsg(player, componentService.getPrefix() + "The warp was successfully set.");
                }
               warpService.saveWarp(arguments[0], player.getLocation(), "abundant.warp." + arguments[0],Material.BEDROCK);
                return true;
            }
            Material material = Material.matchMaterial(arguments[1]);
            if(warpService.warpExists(arguments[0])) {
                warpService.deleteWarp(arguments[0]);
                messagingService.sendMsg(player, componentService.getPrefix() + "The warp was successfully changed.");
            }
            else {
                messagingService.sendMsg(player, componentService.getPrefix() + "The warp was successfully set.");
            }
            warpService.saveWarp(arguments[0], player.getLocation(), "abundant.warp." + arguments[0], material == null ? Material.BEDROCK : material);
        }
        return true;
    }
}
