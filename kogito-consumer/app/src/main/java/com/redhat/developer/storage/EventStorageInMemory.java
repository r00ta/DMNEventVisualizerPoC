package com.redhat.developer.storage;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.redhat.developer.dto.DMNEvent;

@ApplicationScoped
public class EventStorageInMemory implements IEventStorage{

    public ConcurrentHashMap<String, DMNEvent> database;

    @PostConstruct
    void setUp(){
        database = new ConcurrentHashMap<String, DMNEvent>();
    }

    public boolean storeEvent(String key, DMNEvent event){
        if (!database.containsKey(key)){
            database.put(key, event);
            return true;
        }
        return false;
    }

    public DMNEvent getEvent(String key){
        if (database.containsKey(key)){
            return database.get(key);
        }
        return null;
    }

    public List<String> getAllKeys(){
        return Collections.list(database.keys());
    }
}
