package net.abundantmc.abundantskyblock.quests.entity;

public final class PlayerQuestsEntity {
    public PlayerQuestsEntity() {
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "PlayerQuestsEntity[]";
    }

}
