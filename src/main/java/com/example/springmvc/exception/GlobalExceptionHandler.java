package com.example.springmvc.exception;

import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    ErrorObject errorObject = new ErrorObject();
    //Student with that id not found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorObject> studentNotFoundException(StudentNotFoundException studentNotFoundException, WebRequest webRequest){
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(studentNotFoundException.getMessage());
        return new ResponseEntity<>(errorObject,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ErrorObject> duplicateRequestException(DuplicateRequestException duplicateRequestException,WebRequest webRequest){
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(duplicateRequestException.getMessage());
        return new ResponseEntity<>(errorObject,HttpStatus.CONFLICT);
    }
}
