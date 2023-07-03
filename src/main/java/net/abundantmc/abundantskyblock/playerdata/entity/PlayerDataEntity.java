package net.abundantmc.abundantskyblock.playerdata.entity;

import net.abundantmc.abundantskyblock.chat.ChatColor;

import java.util.Objects;
import java.util.UUID;

public final class PlayerDataEntity {
    private final UUID id;
    private int joinNumber;
    private String nickname;
    private ChatColor chatColor;
    private int currentProfileNumber;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PlayerDataEntity) obj;
        return Objects.equals(this.id, that.id) &&
                this.joinNumber == that.joinNumber &&
                Objects.equals(this.nickname, that.nickname) &&
                Objects.equals(this.chatColor, that.chatColor) &&
                this.currentProfileNumber == that.currentProfileNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, joinNumber, nickname, chatColor, currentProfileNumber);
    }

    @Override
    public String toString() {
        return "PlayerData[" +
                "id=" + id + ", " +
                "joinNumber=" + joinNumber + ", " +
                "nickname=" + nickname + ", " +
                "chatColor=" + chatColor + ", " +
                "currentProfileNumber=" + currentProfileNumber + ']';
    }

}
