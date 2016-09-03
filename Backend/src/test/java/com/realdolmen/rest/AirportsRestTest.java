package com.realdolmen.rest;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by WVDAZ49 on 3/09/2016.
 */
public class AirportsRestTest extends RestCallsTest {

    @Test
    public void basicPingTest() {
        given().when().get("/airport/all").then().statusCode(200);
    }


}
