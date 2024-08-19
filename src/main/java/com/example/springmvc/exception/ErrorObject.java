package com.example.springmvc.exception;

import lombok.Data;

@Data
public class ErrorObject {
    private int statusCode;
    private String message;
}
