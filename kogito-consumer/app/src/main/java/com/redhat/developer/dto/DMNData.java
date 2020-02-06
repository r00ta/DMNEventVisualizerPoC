package com.redhat.developer.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMNData {

    @JsonProperty("results")
    public List<EvaluationResult> results;

    @JsonProperty("context")
    public Object context;
}
