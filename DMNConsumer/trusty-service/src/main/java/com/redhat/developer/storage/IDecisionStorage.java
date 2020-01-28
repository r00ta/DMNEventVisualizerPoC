package com.redhat.developer.storage;

import java.util.List;

import com.redhat.developer.domain.DMNModel;
import com.redhat.developer.domain.DMNResult;

public interface IDecisionStorage {

    boolean store(String key, DMNResult result);

    List<String> getAllKeys();

    DMNResult get(String key);

    List<String> getAllModelNames();

    DMNModel getModel(String name);

    boolean storeModel(String key, DMNModel model);
}