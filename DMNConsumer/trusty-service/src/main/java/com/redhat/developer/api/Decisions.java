package com.redhat.developer.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.developer.IDecisionStorageService;
import com.redhat.developer.responses.AvailableKeysResponse;
import com.redhat.developer.responses.DecisionResponse;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/decisions")
public class Decisions {

    @Inject
    IDecisionStorageService storageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AvailableKeysResponse getAllKeys() throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(new AvailableKeysResponse(storageService.getAllDMNResults()));
//
        return new AvailableKeysResponse(storageService.getAllDMNResults());
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public DecisionResponse getDecisionByKey(@PathParam("key") String key){
        ObjectMapper objectMapper = new ObjectMapper();
        return new DecisionResponse(key, storageService.getDMNResultByKey(key));
        }
}
