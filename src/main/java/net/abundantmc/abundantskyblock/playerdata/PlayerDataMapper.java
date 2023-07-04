package net.abundantmc.abundantskyblock.playerdata;

import com.google.inject.Singleton;
import net.abundantmc.abundantskyblock.chat.ChatColor;
import net.abundantmc.abundantskyblock.common.infrastructure.DocumentMapper;
import net.abundantmc.abundantskyblock.playerdata.entity.PlayerDataEntity;
import org.bson.Document;

import java.util.UUID;

@Singleton
public class PlayerDataMapper implements DocumentMapper<PlayerDataEntity> {
    @Override
    public Document mapTo(PlayerDataEntity playerDataEntity) {
        return new Document()
                .append("_id", playerDataEntity.getId().toString())
                .append("joinNumber", playerDataEntity.getJoinNumber())
                .append("nickname", playerDataEntity.getNickname())
                .append("chatColor", playerDataEntity.getChatColor())
                .append("currentProfileNumber", playerDataEntity.getCurrentProfileNumber());
    }

    @Override
    public PlayerDataEntity mapFrom(Document document) {
        return new PlayerDataEntity(
                UUID.fromString(document.getString("_id")),
                document.getInteger("joinNumber"),
                document.getString("nickname"),
                ChatColor.valueOf(document.getString("chatColor")),
                document.getInteger("currentProfileNumber")
        );
    }
}
