package com.example.excercise.infrastructure.springboot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {

    private String message;

    private String details;

    public ErrorDetails(String message) {
        this.message = message;
        this.details = null;
    }

    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }
}
