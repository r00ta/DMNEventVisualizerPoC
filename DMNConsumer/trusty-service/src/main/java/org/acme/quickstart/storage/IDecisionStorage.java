package org.acme.quickstart.storage;

import java.util.List;

import org.acme.quickstart.domain.DMNResult;

public interface IDecisionStorage {
    boolean store(String key, DMNResult result);

    List<String> getAllKeys();

    DMNResult get(String key);
}
