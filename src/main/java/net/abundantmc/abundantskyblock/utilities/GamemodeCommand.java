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
            GameMode gameMode = null;
            if(arguments[0].equalsIgnoreCase("survival") || arguments[0].equalsIgnoreCase("s"))
                gameMode = GameMode.SURVIVAL;
            if(arguments[0].equalsIgnoreCase("creative") || arguments[0].equalsIgnoreCase("c"))
                gameMode = GameMode.CREATIVE;
            if(arguments[0].equalsIgnoreCase("adventure") || arguments[0].equalsIgnoreCase("a"))
                gameMode = GameMode.ADVENTURE;
            if(arguments[0].equalsIgnoreCase("spectator") || arguments[0].equalsIgnoreCase("sp"))
                gameMode = gameMode = GameMode.SPECTATOR;

            if(gameMode != null) {
                player.setGameMode(gameMode);
                messagingService.sendMsg(player,
                        componentService.getPrefix() + "Your gamemode was successfully changed to <gold>" + componentService.capitalize(gameMode.toString().toLowerCase()) + "."
                );

                messagingService.pling(player, 2);
            }
            else{
                messagingService.error(player,
                        "Please enter a valid gamemode."
                );


            }
        }
        return true;
    }


}
