package net.abundantmc.abundantskyblock.profile.entity;

import net.abundantmc.abundantskyblock.collection.entity.PlayerCollectionsEntity;
import net.abundantmc.abundantskyblock.quests.entity.PlayerQuestsEntity;
import net.abundantmc.abundantskyblock.skills.entity.PlayerSkillsEntity;

import java.util.Objects;
import java.util.UUID;

public final class ProfileEntity {
    private final UUID id;
    private UUID playerUUID;
    private int profileNumber;
    private PlayerCollectionsEntity collections;
    private PlayerSkillsEntity skills;
    private int level;
    private int xp;
    private int balance;
    private PlayerQuestsEntity quests;

    public ProfileEntity(
            UUID id,
            UUID playerUUID,
            int profileNumber,
            PlayerCollectionsEntity collections,
            PlayerSkillsEntity skills,
            int level,
            int xp,
            int balance,
            PlayerQuestsEntity quests
    ) {
        this.id = id;
        this.playerUUID = playerUUID;
        this.profileNumber = profileNumber;
        this.collections = collections;
        this.skills = skills;
        this.level = level;
        this.xp = xp;
        this.balance = balance;
        this.quests = quests;
    }

    public UUID getId() {
        return id;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public int getProfileNumber() {
        return profileNumber;
    }

    public PlayerCollectionsEntity getCollections() {
        return collections;
    }

    public PlayerSkillsEntity getSkills() {
        return skills;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getBalance() {
        return balance;
    }

    public PlayerQuestsEntity getQuests() {
        return quests;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public void setProfileNumber(int profileNumber) {
        this.profileNumber = profileNumber;
    }

    public void setCollections(PlayerCollectionsEntity collections) {
        this.collections = collections;
    }

    public void setSkills(PlayerSkillsEntity skills) {
        this.skills = skills;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setQuests(PlayerQuestsEntity quests) {
        this.quests = quests;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ProfileEntity) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.playerUUID, that.playerUUID) &&
                this.profileNumber == that.profileNumber &&
                Objects.equals(this.collections, that.collections) &&
                Objects.equals(this.skills, that.skills) &&
                this.level == that.level &&
                this.xp == that.xp &&
                this.balance == that.balance &&
                Objects.equals(this.quests, that.quests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerUUID, profileNumber, collections, skills, level, xp, balance, quests);
    }

    @Override
    public String toString() {
        return "ProfileEntity[" +
                "id=" + id + ", " +
                "playerUUID=" + playerUUID + ", " +
                "profileNumber=" + profileNumber + ", " +
                "collections=" + collections + ", " +
                "skills=" + skills + ", " +
                "level=" + level + ", " +
                "xp=" + xp + ", " +
                "balance=" + balance + ", " +
                "quests=" + quests + ']';
    }
}
