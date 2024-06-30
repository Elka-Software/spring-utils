package org.elka.helper.object;

public interface ObjectConverter <T, C> {
    T convertTo(C obj);
    C convertFrom(T obj);
}
