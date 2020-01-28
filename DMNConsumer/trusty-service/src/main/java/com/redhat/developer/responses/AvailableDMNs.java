package com.redhat.developer.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableDMNs {
    @JsonProperty("models-name")
    public List<String> names;

    public AvailableDMNs(List<String> names){
        this.names = names;
    }
}
