package com.memopet.memopet.global.common.dto;

import lombok.Data;

@Data
public class RestLoginError {
    private String code;
    private String message;
    private String loginCount;

    public RestLoginError(String code, String message, String loginCount) {
        this.code = code;
        this.message = message;
        this.loginCount = loginCount;
    }

}

