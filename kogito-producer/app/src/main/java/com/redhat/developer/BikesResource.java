package com.redhat.developer;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/dmn-bikes")
public class BikesResource extends AbstractResource {

    private static final String NAMESPACE = "https://kiegroup.org/dmn/_684848B1-E6AA-4AE1-9319-453693B7ED0E";
    private static final String MODEL_NAME = "bikes";

    public BikesResource() {
        super(NAMESPACE, MODEL_NAME);
    }

    @POST
    public Object evaluate(Map<String, Object> variables) {
        return doEvaluate(variables);
    }

}
