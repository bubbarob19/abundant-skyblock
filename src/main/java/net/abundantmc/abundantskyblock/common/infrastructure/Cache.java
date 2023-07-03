package net.abundantmc.abundantskyblock.common.infrastructure;

import java.util.List;
import java.util.Optional;

public interface Cache<T, ID> {
    boolean exists(ID id);
    Optional<T> find(ID id);
    Optional<T> remove(ID id);
    void store(ID id, T t);
    List<T> findAll();
}
