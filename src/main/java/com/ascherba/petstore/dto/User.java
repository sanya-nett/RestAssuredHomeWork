package com.ascherba.petstore.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by aleksandr.scherba on 20.12.2020
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
