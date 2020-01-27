package com.redhat.developer.storage;

import java.util.List;

import com.redhat.developer.domain.DMNResult;

public interface IDecisionStorage {
    boolean store(String key, DMNResult result);

    List<String> getAllKeys();

    DMNResult get(String key);
}
