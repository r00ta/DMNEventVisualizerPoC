package com.redhat.developer.storage;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.redhat.developer.domain.DMNResult;

public class InMemoryDecisionStorage implements IDecisionStorage{

    private ConcurrentHashMap<String, DMNResult> database = new ConcurrentHashMap<>();

    @Override
    public boolean store(String key, DMNResult result) {

        if (database.containsKey(key)) {
            return false;
        }

        database.put(key, result);
        return true;
    }

    @Override
    public DMNResult get(String key) {
        return database.getOrDefault(key, null);
    }

    @Override
    public List<String> getAllKeys() {
        return  Collections.list(database.keys());
    }
}
