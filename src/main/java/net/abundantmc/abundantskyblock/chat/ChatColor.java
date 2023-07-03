package net.abundantmc.abundantskyblock.chat;

public enum ChatColor {
    DEFAULT("<gray>", "group.default"),
    APPLE("<#f58a8a>", "abundant.chat.color.apple"),
    KIWI("<#b9fd53>", "abundant.chat.color.kiwi"),
    ABUNDANT("<#a3ffff>", "abundant.chat.color.abundant");

    private final String colorCode;
    private final String permission;

    ChatColor(String colorCode, String permission) {
        this.colorCode = colorCode;
        this.permission = permission;
    }

    public String getColorCode() {
        return colorCode;
    }

    public String getPermission() {
        return permission;
    }
}
