package com.redhat.developer.api;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.redhat.developer.IDecisionStorageService;
import com.redhat.developer.responses.AvailableDMNs;
import com.redhat.developer.responses.AvailableKeysResponse;
import com.redhat.developer.responses.DMNResponse;
import com.redhat.developer.responses.DecisionResponse;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/dmn")
public class DMN{

    @Inject
    IDecisionStorageService storageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AvailableDMNs getAllDMNs(){
        return new AvailableDMNs(storageService.getAllDMNModelNames());
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_XML)
    public DMNResponse getDMNModelByName(@PathParam("name") String name){
        return new DMNResponse(name, storageService.getDMNModelByName(name));
    }
}
