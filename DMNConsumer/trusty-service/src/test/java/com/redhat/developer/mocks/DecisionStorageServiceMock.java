package com.redhat.developer.mocks;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

import com.redhat.developer.domain.DMNResult;
import io.quarkus.test.Mock;
import com.redhat.developer.DecisionStorageService;

@Mock
@ApplicationScoped
public class DecisionStorageServiceMock extends DecisionStorageService {

    private ConcurrentHashMap<String, DMNResult> database = new ConcurrentHashMap<>();

    public boolean storeDMNResult(String key, DMNResult result) {
        if (database.containsKey(key)){
            return false;
        }

        database.put(key, result);
        return true;
    }

    public DMNResult getDMNResultByKey(String key){
        return database.get(key);
    }

    public List<String> getAllDMNResults(){
        return Collections.list(database.keys());
    }
}
