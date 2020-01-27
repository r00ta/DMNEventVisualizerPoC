package org.acme.quickstart.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMNDecision {

    @JsonProperty("decision-id")
    private String decisionId;

    @JsonProperty("decision-name")
    private String decisionName;

    @JsonProperty("result")
    private Object result;

    @JsonProperty("messages")
    private List<DMNMessage> messages = new ArrayList<>();

    @JsonProperty("status")
    private DecisionEvaluationStatus status;
}
