package com.redhat.developer.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableKeysResponse {
    @JsonProperty("availableKeys")
    public List<String> availableKeys;

    public AvailableKeysResponse(List<String> keys){
        this.availableKeys = keys;
    }
}
