package net.abundantmc.abundantskyblock.common.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BaseCache<T, ID> implements Cache<T, ID> {
    private final HashMap<ID, T> map = new HashMap<>();

    @Override
    public boolean exists(ID id) {
        return map.containsKey(id);
    }

    @Override
    public Optional<T> find(ID id) {
        return Optional.of(map.get(id));
    }

    @Override
    public Optional<T> remove(ID id) {
        return Optional.of(map.remove(id));
    }

    @Override
    public void store(ID id, T t) {
        map.put(id, t);
    }

    @Override
    public List<T> findAll() {
        return map.values().stream().toList();
    }
}
