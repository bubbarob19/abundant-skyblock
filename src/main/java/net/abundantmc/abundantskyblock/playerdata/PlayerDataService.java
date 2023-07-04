package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.chat.ChatColor;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import net.abundantmc.abundantskyblock.playerdata.exception.PlayerDataNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class PlayerDataService {
    private final PlayerDataRepository playerDataRepository;
    private final PlayerDataCache playerDataCache;

    @Inject
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

    public Optional<Long> whenCached(UUID uuid) {
        return playerDataCache.whenCached(uuid);
    }

    public int getTotalPlayerCount() {
        return playerDataRepository.getDocumentCount();
    }

    public void handlePlayerJoin(UUID uuid) {
        Optional<PlayerDataEntity> playerData = findFromUUID(uuid);
        playerData.ifPresentOrElse(playerDataEntity -> {
            playerDataCache.store(uuid, playerDataEntity);
        }, () -> {
            PlayerDataEntity playerDataEntity = createPlayerData(uuid);
            playerDataCache.store(uuid, playerDataEntity);
            playerDataRepository.save(playerDataEntity);
        });
    }

    public void handlePlayerQuit(UUID uuid) {
        Optional<PlayerDataEntity> playerDataEntityOptional = findFromUUID(uuid);
        PlayerDataEntity playerData = playerDataEntityOptional.orElseThrow(() -> new PlayerDataNotFoundException(uuid));

        save(playerData);
        playerDataCache.remove(uuid);
    }
}
