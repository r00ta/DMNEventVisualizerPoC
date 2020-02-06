package com.redhat.developer.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.redhat.developer.responses.AvailableKeysResponse;
import com.redhat.developer.responses.DecisionResponse;
import com.redhat.developer.storage.IEventStorage;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/events")
public class Events {

    @Inject
    IEventStorage storageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AvailableKeysResponse getAllKeys() {
        return new AvailableKeysResponse(storageService.getAllKeys());
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public DecisionResponse getDecisionByKey(@PathParam("key") String key){
        return new DecisionResponse(key, storageService.getEvent(key));
    }
}
