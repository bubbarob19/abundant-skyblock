package net.abundantmc.abundantskyblock.common.infrastructure;

public interface Mapper<T, U> {
    U mapTo(T t);
    T mapFrom(U u);
}
