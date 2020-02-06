package com.redhat.developer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMNEvent {

    @JsonProperty("data")
    public DMNData data;

    @JsonProperty("id")
    public String id;

    @JsonProperty("source")
    public String source;

    @JsonProperty("specversion")
    public String specversion;

    @JsonProperty("type")
    public String type;
}
