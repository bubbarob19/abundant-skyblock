package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.common.infrastructure.BaseCache;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;

import java.util.UUID;

@Singleton
public class PlayerDataCache extends BaseCache<PlayerDataEntity, UUID> {}
