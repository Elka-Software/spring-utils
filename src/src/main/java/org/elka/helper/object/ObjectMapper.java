package org.elka.helper.object;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ObjectMapper <T1, T2> {
    Map<T1, T2> map = new HashMap<>();

    public ObjectMapper<T1, T2> put(T1 key, T2 value) {
        if (!this.map.containsKey(key)) this.map.put(key, value);
        else log.warn("Report mapper already contains the key: {}", key);
        return this;
    }

    public Map<T1, T2> map() {
        return this.map;
    }
}
