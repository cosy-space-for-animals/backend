package com.memopet.memopet.global.common.dto;

import lombok.Data;

@Data
public class RestError {
    private String code;
    private String message;
    public RestError(String code, String message) {
        this.code = code;
        this.message = message;
    }

}

