package net.abundantmc.abundantskyblock.common.mapper;

import com.google.inject.Inject;
import net.abundantmc.abundantskyblock.common.infrastructure.DocumentMapper;
import org.bson.Document;
import org.bukkit.Location;

public class LocationMapper implements DocumentMapper<Location> {
    private final WorldMapper worldMapper;

    @Inject
    public LocationMapper(WorldMapper worldMapper) {
        this.worldMapper = worldMapper;
    }

    @Override
    public Document mapTo(Location location) {
        return new Document()
                .append("world", worldMapper.mapTo(location.getWorld()))
                .append("x", location.getX())
                .append("y", location.getY())
                .append("z", location.getZ())
                .append("yaw", location.getYaw())
                .append("pitch", location.getPitch());
    }

    @Override
    public Location mapFrom(Document document) {
        return new Location(
                worldMapper.mapFrom(document.getString("world")),
                document.getDouble("x"),
                document.getDouble("y"),
                document.getDouble("z"),
                document.getDouble("yaw").floatValue(),
                document.getDouble("pitch").floatValue()
        );
    }
}
