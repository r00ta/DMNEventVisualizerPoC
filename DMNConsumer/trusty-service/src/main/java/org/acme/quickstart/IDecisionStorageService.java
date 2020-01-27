package org.acme.quickstart;

import java.util.List;

import org.acme.quickstart.domain.DMNResult;

public interface IDecisionStorageService {

    boolean storeDMNResult(String key, DMNResult result);

    DMNResult getDMNResultByKey(String key);

    List<String> getAllDMNResults();
}
