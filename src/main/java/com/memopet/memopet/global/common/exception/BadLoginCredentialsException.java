package com.memopet.memopet.global.common.exception;

public class BadLoginCredentialsException extends RuntimeException{

    private String message;

    public BadLoginCredentialsException(String message) {
        super(message);
        this.message = message;
    }
}