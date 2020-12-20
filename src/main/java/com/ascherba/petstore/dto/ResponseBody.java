package com.ascherba.petstore.dto;

import lombok.Data;

/**
 * Created by aleksandr.scherba on 20.12.2020
 */
@Data
public class ResponseBody {
    private int code;
    private String type;
    private String message;
}
