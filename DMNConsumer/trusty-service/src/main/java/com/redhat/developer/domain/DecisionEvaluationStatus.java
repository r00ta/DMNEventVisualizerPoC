package com.redhat.developer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DecisionEvaluationStatus {
        @JsonProperty("NOT_EVALUATED")
        NOT_EVALUATED,
        @JsonProperty("EVALUATING")
        EVALUATING,
        @JsonProperty("SUCCEEDED")
        SUCCEEDED,
        @JsonProperty("SKIPPED")
        SKIPPED,
        @JsonProperty("FAILED")
        FAILED;
}
