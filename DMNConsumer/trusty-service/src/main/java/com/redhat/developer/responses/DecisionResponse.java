package com.redhat.developer.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.developer.domain.DMNResult;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class DecisionResponse {
    @JsonProperty("decision")
    public DMNResult decision;

    @JsonProperty("key")
    public String key;

    public DecisionResponse(String key, DMNResult decision){
        this.decision = decision;
        this.key = key;
    }
}
