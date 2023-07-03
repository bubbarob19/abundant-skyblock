package net.abundantmc.abundantskyblock.warp.entity;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;

public record WarpEntity(UUID id, String name, Location location, String permission, Material icon) {}
