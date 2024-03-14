package com.example.securityzerotoend.exceptionhandlers.exceptions;

import lombok.Data;

@Data
public class ExceptionResponse {
    private Boolean error;
    private String message;
}
