package com.redhat.developer;

import java.util.List;

import javax.inject.Singleton;

import com.redhat.developer.domain.DMNModel;
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

        @Override
        public boolean storeDMNModel(String key, DMNModel model) {
                return storageManager.storeModel(key, model);
        }

        @Override
        public DMNResult getDMNResultByKey(String key){
                return storageManager.get(key);
        }

        @Override
        public DMNModel getDMNModelByName(String name) {
                return storageManager.getModel(name);
        }

        @Override
        public List<String> getAllDMNResults(){
                return storageManager.getAllKeys();
        }

        @Override
        public List<String> getAllDMNModelNames() {
                return storageManager.getAllModelNames();
        }
}
