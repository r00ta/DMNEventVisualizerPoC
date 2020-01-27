package com.redhat.developer;

import javax.inject.Inject;

import com.redhat.developer.domain.DMNResult;
import com.redhat.developer.generators.DMNResultGenerator;
import com.redhat.developer.mocks.DecisionStorageServiceMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class EventsSpecs {

    @Inject
    DecisionStorageServiceMock storageService;

    @Test
    public void testEventEndpoint() {
        DMNResult dmnResult = DMNResultGenerator.GetDMNResult();
        storageService.storeDMNResult("pippo", dmnResult);
        given()
                .when().get("/events")
                .then()
                .statusCode(200)
                .body(is("pippo"));

        given()
                .when().get("/events/pippo")
                .then()
                .statusCode(200)
                .body(containsString("Traffic Violation"));
    }
}
