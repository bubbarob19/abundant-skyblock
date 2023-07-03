package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.chat.ChatColor;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class PlayerDataService {
    private final PlayerDataRepository playerDataRepository;
    private final PlayerDataCache playerDataCache;

    public PlayerDataService(PlayerDataRepository playerDataRepository, PlayerDataCache playerDataCache) {
        this.playerDataRepository = playerDataRepository;
        this.playerDataCache = playerDataCache;
    }

    public Optional<PlayerDataEntity> findFromUUID(UUID uuid) {
        if (playerDataCache.exists(uuid)) {
            return playerDataCache.find(uuid);
        } else {
            return playerDataRepository.findFromID(uuid.toString());
        }
    }

    public List<PlayerDataEntity> allOnlinePlayerData() {
        return playerDataCache.findAll();
    }

    public PlayerDataEntity createPlayerData(UUID uuid) {
        return new PlayerDataEntity(
                uuid,
                0,
                null,
                ChatColor.DEFAULT,
                1
        );
    }

    public void save(PlayerDataEntity playerDataEntity) {
        playerDataRepository.save(playerDataEntity);
    }

    public void handlePlayerJoin(Player player) {
        UUID uuid = player.getUniqueId();
        Optional<PlayerDataEntity> playerData = findFromUUID(uuid);
        playerData.ifPresentOrElse(playerDataEntity -> {
            playerDataCache.store(uuid, playerDataEntity);
        }, () -> {
            PlayerDataEntity playerDataEntity = createPlayerData(uuid);
            playerDataCache.store(uuid, playerDataEntity);
            playerDataRepository.save(playerDataEntity);
        });
    }
}
