package com.redhat.developer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testMonitorEndpoint() {
        given()
          .when().get("/monitor")
          .then()
             .statusCode(200)
             .body(is("0"));
    }

}