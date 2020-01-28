package com.redhat.developer.storage;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.redhat.developer.domain.DMNModel;
import com.redhat.developer.domain.DMNResult;

public class InMemoryDecisionStorage implements IDecisionStorage{

    private ConcurrentHashMap<String, DMNResult> resultDatabase = new ConcurrentHashMap<>();

    private ConcurrentHashMap<String, DMNModel> modelDatabase = new ConcurrentHashMap<>();

    @Override
    public boolean store(String key, DMNResult result) {

        if (resultDatabase.containsKey(key)) {
            return false;
        }

        resultDatabase.put(key, result);
        return true;
    }

    @Override
    public DMNResult get(String key) {
        return resultDatabase.getOrDefault(key, null);
    }

    @Override
    public List<String> getAllModelNames() {
        return  Collections.list(modelDatabase.keys());
    }

    @Override
    public DMNModel getModel(String name) {
        return modelDatabase.getOrDefault(name, null);
    }

    @Override
    public boolean storeModel(String key, DMNModel model) {
        if (modelDatabase.containsKey(key)) {
            return false;
        }

        modelDatabase.put(key, model);
        return true;
    }

    @Override
    public List<String> getAllKeys() {
        return  Collections.list(resultDatabase.keys());
    }
}
