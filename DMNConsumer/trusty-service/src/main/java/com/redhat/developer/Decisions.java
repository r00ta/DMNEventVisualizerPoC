package com.redhat.developer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/decisions")
public class Decisions {

    @Inject
    IDecisionStorageService storageService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllKeys() {
        return String.join(", ", storageService.getAllDMNResults());
    }

    @GET
    @Path("/{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDecisionByKey(@PathParam("key") String key){
        ObjectMapper objectMapper = new ObjectMapper();
        String decision = null;
        try {
            decision = objectMapper.writeValueAsString(storageService.getDMNResultByKey(key));
        } catch (JsonProcessingException e) {
            System.out.print("Error serializing the object.");
            e.printStackTrace();
        }

        return decision;
    }
}
