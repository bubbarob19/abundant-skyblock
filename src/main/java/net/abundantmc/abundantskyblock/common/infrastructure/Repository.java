package net.abundantmc.abundantskyblock.common.infrastructure;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findFromID(ID id);
    List<T> findAll();
    Optional<T> delete(ID id);
    void save(T t);
}
