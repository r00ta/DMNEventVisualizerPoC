package com.redhat.developer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DMNMessage {

    // private DMNMessageSeverityKS severity;

    @JsonProperty("message")
    private String message;

    // private DMNMessageType messageType;

    @JsonProperty("source-id")
    private String sourceId;

}
