package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specs {
    public static RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder().
                setBaseUri(url).
                setContentType(ContentType.JSON).
                build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder().
                expectStatusCode(200).build();

    }

    public static ResponseSpecification responseSpecBad400() {
        return new ResponseSpecBuilder().
                expectStatusCode(400).build();

    }

    public static ResponseSpecification responseSpecCreated201() {
        return new ResponseSpecBuilder().
                expectStatusCode(201).build();

    }

    public static ResponseSpecification responseSpecNotFound404() {
        return new ResponseSpecBuilder().
                expectStatusCode(404).build();

    }

    public static void installSpec(RequestSpecification request, ResponseSpecification response) {
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;

    }

}
