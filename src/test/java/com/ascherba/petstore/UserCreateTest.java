package com.ascherba.petstore;

import com.ascherba.petstore.dto.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * Created by aleksandr.scherba on 20.12.2020
 */
public class UserCreateTest extends BasicUserTest {

    /**
     * <b>Success to create user with full data</b><br><br>
     * 1. Make create user POST request with valid data <br>
     * 2. Check that response status code equal to 200 <br>
     * 3. Check that response has message equal to user id <br>
     * 4. Check that response has code equal to 200 <br>
     * 5. Check that response has type equal to unknown
     */
    @Test
    public void shouldUserCreationSuccessfulWithValidData() {
        ResponseBody responseBody = createUser(testUser)
                .then()
                .statusCode(200)
                .extract().body().as(ResponseBody.class);
        String expectedMessageValue = String.valueOf(testUser.getId());
        Assertions.assertEquals(expectedMessageValue, responseBody.getMessage());
        // it seems there is no need to check the code and type, or it does not work correctly
        Assertions.assertEquals(200, responseBody.getCode());
        Assertions.assertEquals("unknown", responseBody.getType());
    }

    /**
     * <b>Failed to create user with empty data</b><br><br>
     * 1. Make create user POST request with empty data <br>
     * 2. Check that response status code equal to 200 <br>
     * 3. Check that response has code equal to 200 <br>
     * 4. Check that response has type equal to unknown <br>
     * 5. Check that response has message equal to 0
     */
    @Test
    public void shouldUserCreationFailedWithEmptyData() {
        ResponseBody responseBody = createUser(Collections.emptyMap())
                .then()
                .statusCode(200)
                .extract().body().as(ResponseBody.class);
        Assertions.assertEquals("0", responseBody.getMessage());
        // it seems there is no need to check the code and type, or it does not work correctly
        Assertions.assertEquals(200, responseBody.getCode());
        Assertions.assertEquals("unknown", responseBody.getType());
    }

}
