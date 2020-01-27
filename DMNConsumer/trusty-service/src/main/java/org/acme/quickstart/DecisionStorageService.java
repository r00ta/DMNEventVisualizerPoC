package org.acme.quickstart;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.acme.quickstart.domain.DMNResult;
import org.acme.quickstart.storage.IDecisionStorage;
import org.acme.quickstart.storage.InMemoryDecisionStorage;

@Singleton
public class DecisionStorageService implements IDecisionStorageService {

        // TODO: How to inject with quarkus?
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
