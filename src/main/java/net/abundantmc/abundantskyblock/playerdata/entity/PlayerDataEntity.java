package net.abundantmc.abundantskyblock.playerdata.entity;

import com.google.common.base.Objects;
import net.abundantmc.abundantskyblock.chat.ChatColor;

import java.util.UUID;

public final class PlayerDataEntity {
    // Persistent
    private final UUID id;
    private int joinNumber;
    private String nickname;
    private ChatColor chatColor;
    private int currentProfileNumber;

    // Non-Persistent
    private boolean firstTime;

    public PlayerDataEntity(
            UUID id,
            int joinNumber,
            String nickname,
            ChatColor chatColor,
            int currentProfileNumber
    ) {
        this.id = id;
        this.joinNumber = joinNumber;
        this.nickname = nickname;
        this.chatColor = chatColor;
        this.currentProfileNumber = currentProfileNumber;
    }

    // Permanent

    public UUID getId() {
        return id;
    }

    public int getJoinNumber() {
        return joinNumber;
    }

    public void setJoinNumber(int joinNumber) {
        this.joinNumber = joinNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public void setChatColor(ChatColor chatColor) {
        this.chatColor = chatColor;
    }

    public int getCurrentProfileNumber() {
        return currentProfileNumber;
    }

    public void setCurrentProfileNumber(int currentProfileNumber) {
        this.currentProfileNumber = currentProfileNumber;
    }

    // Non-Permanent

    public boolean isFirstTime() {
        return firstTime;
    }

    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
