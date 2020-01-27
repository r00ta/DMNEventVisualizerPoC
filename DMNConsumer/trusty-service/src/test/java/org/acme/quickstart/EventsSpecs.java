package org.acme.quickstart;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.quickstart.domain.DMNResult;
import org.acme.quickstart.generators.DMNResultGenerator;
import org.acme.quickstart.mocks.DecisionStorageServiceMock;
import org.acme.quickstart.mocks.KafkaConsumerServiceMock;
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
