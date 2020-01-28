package com.redhat.developer;

import java.util.List;

import com.redhat.developer.domain.DMNModel;
import com.redhat.developer.domain.DMNResult;

public interface IDecisionStorageService {

    boolean storeDMNResult(String key, DMNResult result);

    boolean storeDMNModel(String key, DMNModel model);

    DMNResult getDMNResultByKey(String key);

    DMNModel getDMNModelByName(String name);

    List<String> getAllDMNResults();

    List<String> getAllDMNModelNames();

}
