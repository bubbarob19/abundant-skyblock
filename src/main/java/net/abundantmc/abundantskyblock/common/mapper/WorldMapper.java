package net.abundantmc.abundantskyblock.common.mapper;

import net.abundantmc.abundantskyblock.common.infrastructure.Mapper;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldMapper implements Mapper<World, String> {
    @Override
    public String mapTo(World world) {
        return world.getName();
    }

    @Override
    public World mapFrom(String name) {
        return Bukkit.getWorld(name);
    }
}
