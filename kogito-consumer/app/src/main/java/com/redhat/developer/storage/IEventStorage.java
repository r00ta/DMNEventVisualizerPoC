package com.redhat.developer.storage;

import java.util.List;

import com.redhat.developer.dto.DMNEvent;

public interface IEventStorage {
    boolean storeEvent(String key, DMNEvent event);

    DMNEvent getEvent(String key);

    List<String> getAllKeys();
}
