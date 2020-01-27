package com.redhat.developer;

import java.util.List;

import javax.inject.Singleton;

import com.redhat.developer.domain.DMNResult;
import com.redhat.developer.storage.IDecisionStorage;
import com.redhat.developer.storage.InMemoryDecisionStorage;

@Singleton
public class DecisionStorageService implements IDecisionStorageService {

        private IDecisionStorage storageManager = new InMemoryDecisionStorage();

        @Override
        public boolean storeDMNResult(String key, DMNResult result) {
                return storageManager.store(key, result);
        }

        public DMNResult getDMNResultByKey(String key){
                return storageManager.get(key);
        }

        public List<String> getAllDMNResults(){
                return storageManager.getAllKeys();
        }

}
