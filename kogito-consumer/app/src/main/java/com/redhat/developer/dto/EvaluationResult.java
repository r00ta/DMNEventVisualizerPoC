package com.redhat.developer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluationResult {

    @JsonProperty("id")
    public String id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("evaluationStatus")
    public String evaluationStatus;

    @JsonProperty("result")
    public String result;

    @JsonProperty("messages")
    public List<String> messages;
}