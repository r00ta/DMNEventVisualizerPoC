package com.redhat.developer;

import java.util.List;

import com.redhat.developer.domain.DMNResult;

public interface IDecisionStorageService {

    boolean storeDMNResult(String key, DMNResult result);

    DMNResult getDMNResultByKey(String key);

    List<String> getAllDMNResults();
}
