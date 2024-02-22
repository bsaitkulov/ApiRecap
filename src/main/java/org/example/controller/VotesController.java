package org.example.controller;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class VotesController {

    private String baseUrl;
    private String headerKey;
    private String headerValue;

    private RequestSpecification spec;

    public VotesController(String baseUrl, String headerKey, String headerValue) {
        this.baseUrl = baseUrl;
        this.headerKey = headerKey;
        this.headerValue = headerValue;
        this.spec = given()
                .baseUri(baseUrl)
                .header(headerKey, headerValue);
    }

    public Response get(String endpoint){
        log.info("GET Request: {}", endpoint);
        return given()
                .spec(this.spec)
                .get(endpoint);
    }

    public Response post(String endpoint, String payload){
        log.info("POST Request: {}", endpoint);
        log.info("Payload: {}", payload);
        return given()
                .spec(this.spec)
                .contentType(ContentType.JSON)
                .body(payload)
                .post(endpoint);

    }

}
