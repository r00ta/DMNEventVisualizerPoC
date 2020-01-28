package com.redhat.developer.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.redhat.developer.domain.DMNModel;

public class DMNResponse {
    @JsonProperty("model")
    public DMNModel model;

    @JsonProperty("name")
    public String name;

    public DMNResponse(String name, DMNModel model){
        this.name = name;
        this.model = model;
    }
}
