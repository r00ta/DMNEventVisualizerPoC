package com.redhat.developer;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.kogito.dmn.rest.DMNEvaluationErrorException;
import org.kie.kogito.dmn.rest.DMNResult;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AbstractResource {

    @Inject
    DMNRuntime rt;

    private final String namespace;
    private final String modelName;

    public AbstractResource(String namespace, String modelName) {
        this.namespace = namespace;
        this.modelName = modelName;
    }

    protected Object doEvaluate(Map<String, Object> variables) {
        //String namespace = "https:////github.com/kiegroup/kogito-examples/dmn-quarkus-listener-example";
        //String modelName = "Loan Eligibility";

        DMNModel dmnModel = rt.getModel(namespace, modelName);

        DMNContext dmnContext = rt.newContext();
        variables.forEach(dmnContext::set);

        DMNResult result = new DMNResult(rt.evaluateAll(dmnModel, dmnContext));
        if (!result.hasErrors()) {
            return result.getDmnContext();
        } else {
            throw new DMNEvaluationErrorException(result);
        }
    }

    @Provider
    public static class DMNEvaluationErrorExceptionMapper implements ExceptionMapper<DMNEvaluationErrorException> {

        public Response toResponse(DMNEvaluationErrorException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getResult()).build();
        }
    }

}