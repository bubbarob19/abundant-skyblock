package net.abundantmc.abundantskyblock.collection.entity;

public final class PlayerCollectionsEntity {
    public PlayerCollectionsEntity() {
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
        return "PlayerCollectionsEntity[]";
    }

}
