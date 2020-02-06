package com.redhat.developer.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.developer.dto.DMNEvent;

public class DecisionResponse {
    @JsonProperty("event")
    public DMNEvent event;

    @JsonProperty("key")
    public String key;

    public DecisionResponse(String key, DMNEvent event){
        this.event = event;
        this.key = key;
    }
}
