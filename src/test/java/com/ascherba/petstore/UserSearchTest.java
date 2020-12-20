package com.ascherba.petstore;

import com.ascherba.petstore.dto.ResponseBody;
import com.ascherba.petstore.dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by aleksandr.scherba on 20.12.2020
 */
public class UserSearchTest extends BasicUserTest {

    /**
     * <b>Success found exist user</b><br><br>
     * 1. Make create user POST request with user data <br>
     * 2. Make find user GET request with username from first step <br>
     * 3. Check that response status code equal to 200 <br>
     * 4. Check that response has user data equal to user data from first step
     */
    @Test
    public void shouldFoundExistUser() {
        createUser(testUser);
        User actualUser = findUser(testUser.getUsername())
                .then()
                .statusCode(200)
                .extract().body().as(User.class);
        Assertions.assertEquals(testUser, actualUser, "User data is different");
    }

    /**
     * <b>Failed to found not exist user</b><br><br>
     * 1. Make find user GET request with not exist username<br>
     * 2. Check that response status code equal to 404 <br>
     * 3. Check that response has message equal to User not found<br>
     * 4. Check that response has code equal to 1 <br>
     * 5. Check that response has type equal to error
     */
    @Test
    public void shouldNotFoundUnknownUser() {
        ResponseBody responseBody = findUser("UNKNOWN#123")
                .then()
                .statusCode(404)
                .extract().body().as(ResponseBody.class);
        Assertions.assertEquals("User not found", responseBody.getMessage());
        Assertions.assertEquals(1, responseBody.getCode());
        Assertions.assertEquals("error", responseBody.getType());
    }

}
