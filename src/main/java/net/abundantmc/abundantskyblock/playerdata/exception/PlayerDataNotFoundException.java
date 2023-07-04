package net.abundantmc.abundantskyblock.playerdata.exception;

import java.util.UUID;

public class PlayerDataNotFoundException extends RuntimeException {
    public PlayerDataNotFoundException(UUID uuid) {
        super("PlayerData was not found for the player with uuid: " + uuid);
    }
}
