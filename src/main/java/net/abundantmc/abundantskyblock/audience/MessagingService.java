package net.abundantmc.abundantskyblock.audience;

import com.google.inject.Inject;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class MessagingService {
    private static final int MILLI_IN_SEC = 1000;
    private static final HashMap<Player, BossBar> bossBars = new HashMap<>();

    private final ComponentService componentService;

    @Inject
    public MessagingService(ComponentService componentService) {
        this.componentService = componentService;
    }

    public Audience audienceOfAllPlayers() {
        return Audience.audience(Bukkit.getOnlinePlayers());
    }

    public Audience audienceOfPlayersWithPerm(String permission) {
        List<Player> playerList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission(permission))
                playerList.add(player);
        }
        return Audience.audience(playerList);
    }

    public Audience audienceOfPlayersWithCond(Function<Player, Boolean> condition) {
        List<Player> playerList = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (condition.apply(player))
                playerList.add(player);
        }
        return Audience.audience(playerList);
    }

    public void sendMsg(Audience audience, String... messages) {
        Component component = componentService.getComponent(messages);
        if (component != null) audience.sendMessage(component);
    }

    public void sendMsg(Audience audience, Component... messages) {
        for (Component message : messages) {
            audience.sendMessage(message);
        }
    }

    public void error(Audience audience, String message) {
        audience.sendMessage(componentService.getErrorMessage(message));
        playSound(audience, org.bukkit.Sound.ENTITY_VILLAGER_NO);
    }

    /**
     * Sends a title to an audience
     * @param audience Player to send title to
     * @param titleMessage Message on Title
     * @param subTitleMessage Message on Subtitle
     * @param length Length of Title in TICKS (20 per 1 second)
     * @param fadeIn Length of FadeIN in TICKS (20 per 1 second)
     * @param fadeOut Length of FadeOUT in TICKS (20 per 1 second)
     */
    public void sendTitle(Audience audience, String titleMessage, String subTitleMessage, long fadeIn, long length, long fadeOut) {
        if (titleMessage == null) titleMessage = "";
        if (subTitleMessage == null) subTitleMessage = "";
        Title.Times times = Title.Times.times(Duration.ofMillis(MILLI_IN_SEC * fadeIn / 20), Duration.ofMillis(MILLI_IN_SEC * length / 20), Duration.ofMillis(MILLI_IN_SEC * fadeOut / 20));
        Title title = Title.title(componentService.getComponent(titleMessage), componentService.getComponent(subTitleMessage), times);
        audience.showTitle(title);
    }

    public void sendActionBar(Audience audience, String message) {
        audience.sendActionBar(componentService.getComponent(message));
    }

    public void playSound(Audience audience, org.bukkit.Sound soundValue, float pitch, float volume) {
        Sound sound = Sound.sound(soundValue.key(), Sound.Source.MASTER, volume, pitch);
        audience.playSound(sound, Sound.Emitter.self());
    }

    public void playSound(Audience audience, org.bukkit.Sound soundValue, float pitch) {
        playSound(audience, soundValue, pitch, 1);
    }

    public void playSound(Audience audience, org.bukkit.Sound soundValue) {
        playSound(audience, soundValue, 1, 1);
    }

    public void pling(Audience audience, float soundLevel) {
        playSound(audience, org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, soundLevel);
    }

    public void ding(Audience audience, float soundLevel) {
        playSound(audience, org.bukkit.Sound.ENTITY_ARROW_HIT_PLAYER, soundLevel);
    }

    public void pop(Audience audience, float soundLevel) {
        playSound(audience, org.bukkit.Sound.ENTITY_CHICKEN_EGG, soundLevel);
    }
}
