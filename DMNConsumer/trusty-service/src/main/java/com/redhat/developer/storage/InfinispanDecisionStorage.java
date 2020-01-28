package com.redhat.developer.storage;

import java.util.List;

import com.redhat.developer.domain.DMNModel;
import com.redhat.developer.domain.DMNResult;

public class InfinispanDecisionStorage implements IDecisionStorage {

    @Override
    public boolean store(String key, DMNResult result) {
        // TODO
        return false;
    }

    @Override
    public List<String> getAllKeys() {
        // TODO
        return null;
    }

    @Override
    public DMNResult get(String key) {
        // TODO
        return null;
    }

    @Override
    public List<String> getAllModelNames() {
        return null;
    }

    @Override
    public DMNModel getModel(String name) {
        return null;
    }

    @Override
    public boolean storeModel(String key, DMNModel model) {
        return false;
    }
}
