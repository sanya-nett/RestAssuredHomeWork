package com.ascherba.petstore;

import com.ascherba.petstore.dto.User;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

import static io.restassured.RestAssured.given;

/**
 * Created by aleksandr.scherba on 20.12.2020
 */
public class BasicUserTest {

    private final static String CREATE_USER_ENDPOINT = "/user";
    private final static String FIND_USER_ENDPOINT = "/user/{username}";

    public User testUser;

    private static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("https://petstore.swagger.io/v2")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();

    private static final ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .build();

    @BeforeAll
    public static void configureRestAssured() {
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL), new ResponseLoggingFilter(LogDetail.ALL));
    }

    @BeforeEach
    public void configureTestUser() {
        testUser = new User()
                .setId(Math.abs(new Random().nextLong()))
                .setUsername(String.format("ascherba-%s", System.currentTimeMillis()))
                .setFirstName("Alexandr")
                .setLastName("Scherba")
                .setEmail("ascherba@gmail.com")
                .setPassword("qwerty123")
                .setPhone("+7-911-256-13-00")
                .setUserStatus(1);
    }

    public Response createUser(Object object) {
        return given().when().body(object).post(CREATE_USER_ENDPOINT).then().extract().response();
    }

    public Response findUser(String username) {
        return given().get(FIND_USER_ENDPOINT, username).then().extract().response();
    }
}
