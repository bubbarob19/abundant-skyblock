package net.abundantmc.abundantskyblock.skills.entity;

public final class PlayerSkillsEntity {
    public PlayerSkillsEntity() {
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
        return "PlayerSkillsEntity[]";
    }

}
