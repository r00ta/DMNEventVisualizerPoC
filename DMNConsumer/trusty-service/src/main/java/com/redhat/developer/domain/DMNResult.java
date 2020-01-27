package com.redhat.developer.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMNResult {

    @JsonProperty("model-namespace")
    private String modelNameSpace;

    @JsonProperty("model-name")
    private String modelName;

    @JsonProperty("decision-name")
    private List<String> decisionNames = new ArrayList<>();

    @JsonProperty("dmn-context")
    private Map<String, Object> dmnContext = new HashMap<>();

    @JsonProperty("messages")
    private List<DMNMessage> messages = new ArrayList<>();

    @JsonProperty("decision-results")
    private Map<String, DMNDecision> decisionResults = new HashMap<>();

    public String getModelNameSpace(){
        return modelNameSpace;
    }
}
