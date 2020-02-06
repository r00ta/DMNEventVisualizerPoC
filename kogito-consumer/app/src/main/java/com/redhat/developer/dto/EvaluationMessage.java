package com.redhat.developer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluationMessage {
    @JsonProperty("message")
    public String message;

    @JsonProperty("type")
    public String type;

    @JsonProperty("description")
    public String description;

    @JsonProperty("level")
    public String level;

    @JsonProperty("sourceId")
    public String sourceId;
}
