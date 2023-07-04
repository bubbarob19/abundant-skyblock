package net.abundantmc.abundantskyblock.common.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BaseCache<T, ID> implements Cache<T, ID> {
    private final HashMap<ID, T> entities = new HashMap<>();
    private final HashMap<ID, Long> whenCached = new HashMap<>();

    @Override
    public boolean exists(ID id) {
        return entities.containsKey(id);
    }

    @Override
    public Optional<T> find(ID id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Optional<T> remove(ID id) {
        whenCached.remove(id);
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public void store(ID id, T t) {
        entities.put(id, t);
        whenCached.put(id, System.currentTimeMillis());
    }

    @Override
    public List<T> findAll() {
        return entities.values().stream().toList();
    }

    public Optional<Long> whenCached(ID id) {
        return Optional.ofNullable(whenCached.get(id));
    }
}
