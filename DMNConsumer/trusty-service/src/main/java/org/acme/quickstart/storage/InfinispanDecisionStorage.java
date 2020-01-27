package org.acme.quickstart.storage;

import java.util.List;

import org.acme.quickstart.domain.DMNResult;

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
}
