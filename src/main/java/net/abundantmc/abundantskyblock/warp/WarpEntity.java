package net.abundantmc.abundantskyblock.warp;

import org.bukkit.Location;
import org.bukkit.Material;

public record WarpEntity(String id, String name, Location location, String permission, Material icon) {}
