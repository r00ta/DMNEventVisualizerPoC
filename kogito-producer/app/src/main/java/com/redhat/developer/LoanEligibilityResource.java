package org.kie.kogito;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Map;

import com.redhat.developer.AbstractResource;

@Path("/dmn-loan-eligibility")
public class LoanEligibilityResource extends AbstractResource {

    private static final String NAMESPACE = "https://github.com/kiegroup/kogito-examples/dmn-quarkus-listener-example";
    private static final String MODEL_NAME = "Loan Eligibility";

    public LoanEligibilityResource() {
        super(NAMESPACE, MODEL_NAME);
    }

    @POST
    public Object evaluate(Map<String, Object> variables) {
        return doEvaluate(variables);
    }

}
