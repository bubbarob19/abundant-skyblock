package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.audience.ComponentService;
import net.abundantmc.abundantskyblock.audience.MessagingService;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class WarpService {
    private final MessagingService messagingService;
    private final ComponentService componentService;
    private final WarpRepository warpRepository;

    @Inject
    public WarpService(MessagingService messagingService,
                       ComponentService componentService,
                       WarpRepository warpRepository) {
        this.messagingService = messagingService;
        this.componentService = componentService;
        this.warpRepository = warpRepository;
    }

    public void warpPlayer(WarpEntity warpEntity, Player player) {
        teleportPlayerToWarp(warpEntity, player);
        sendSuccessMessage(warpEntity, player);
    }

    public boolean warpExists(String name) {
        return warpRepository.findFromName(name).isPresent();
    }

    public WarpEntity saveWarp(String name, Location location, String permission, Material icon) {
        WarpEntity warpEntity = new WarpEntity(
                UUID.randomUUID(),
                name,
                location,
                permission,
                icon
        );
        warpRepository.save(warpEntity);
        return warpEntity;
    }

    public Optional<WarpEntity> deleteWarp(String name) {
        Optional<WarpEntity> warpEntityOptional = warpRepository.findFromName(name);
        warpEntityOptional.ifPresent(warpEntity -> warpRepository.delete(warpEntity.id().toString()));
        return warpEntityOptional;
    }

    public List<WarpEntity> findAllWarps() {
        return warpRepository.findAll();
    }

    public Optional<WarpEntity> findFromName(String name) {
        return warpRepository.findFromName(name);
    }
    private void teleportPlayerToWarp(WarpEntity warpEntity, Player player) {
        player.teleport(warpEntity.location());
    }

    private void sendSuccessMessage(WarpEntity warpEntity, Player player) {
        messagingService.sendMsg(player,
                componentService.getPrefix() + "You were warped to the warp: <gold>" + warpEntity.name()
        );
        messagingService.pling(player, 2);
    }
}
