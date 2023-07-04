package net.abundantmc.abundantskyblock.warp.exception;

public class WarpNotFoundException extends RuntimeException {
    public WarpNotFoundException(String name) {
        super("Warp was not found for name: " + name);
    }
}
