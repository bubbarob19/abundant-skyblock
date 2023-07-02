package net.abundantmc.abundantskyblock.utilities;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GamemodeCommand implements CommandExecutor {
    private final MessagingService messagingService;
    private final ComponentService componentService;

    @Inject
    public GamemodeCommand(MessagingService messagingService, ComponentService componentService) {
        this.messagingService = messagingService;
        this.componentService = componentService;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] arguments) {
        // implement here
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(arguments[0].equalsIgnoreCase("survival"))
                player.setGameMode(GameMode.SURVIVAL);
            if(arguments[0].equalsIgnoreCase("creative"))
                player.setGameMode(GameMode.CREATIVE);
            if(arguments[0].equalsIgnoreCase("adventure"))
                player.setGameMode(GameMode.ADVENTURE);
            if(arguments[0].equalsIgnoreCase("spectator"))
                player.setGameMode(GameMode.SPECTATOR);

            messagingService.sendMsg(player,
                    componentService.getPrefix() + "Your gamemode was successfully changed."
            );
            messagingService.pling(player, 2);
        }
        return true;
    }


}
