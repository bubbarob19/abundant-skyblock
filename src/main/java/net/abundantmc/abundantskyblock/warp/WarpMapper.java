package net.abundantmc.abundantskyblock.warp;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.common.infrastructure.DocumentMapper;
import net.abundantmc.abundantskyblock.common.mapper.LocationMapper;
import org.bson.Document;
import org.bukkit.Material;

import java.util.UUID;

public class WarpMapper implements DocumentMapper<WarpEntity> {
    private final LocationMapper locationMapper;

    @Inject
    public WarpMapper(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    @Override
    public Document mapTo(WarpEntity warpEntity) {
        return new Document()
                .append("_id", warpEntity.id().toString())
                .append("name", warpEntity.name())
                .append("location", locationMapper.mapTo(warpEntity.location()))
                .append("permission", warpEntity.permission())
                .append("icon", warpEntity.icon());
    }

    @Override
    public WarpEntity mapFrom(Document document) {
        return new WarpEntity(
                UUID.fromString(document.getString("_id")),
                document.getString("name"),
                locationMapper.mapFrom(document.get("location", Document.class)),
                document.getString("permission"),
                Material.valueOf(document.getString("icon"))
        );
    }
}
