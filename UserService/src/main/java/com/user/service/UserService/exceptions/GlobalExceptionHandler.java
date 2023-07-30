package com.user.service.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.service.UserService.payload.ApiResponse;

//centralized exception handler for whole project...

//@RestControllerAdvice is an annotation in Spring that is used to create a global exception handler
//that applies to all the @RestController annotated classes within your application.
//It combines the functionality of the @ControllerAdvice and @ResponseBody annotations, 
//making it perfect for building RESTful APIs 1. By placing the @RestControllerAdvice annotation on a class,
//you can define methods that handle exceptions thrown by any controller within your application.
//These methods can process the exceptions and return appropriate responses to the clients, 
//such as error messages or custom error payloads in JSON format
@RestControllerAdvice
public class GlobalExceptionHandler {

    // pure project me kahin bhi ResourceNotFoundException generate hogi toh ye vala handler call ho jayega
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex) {

        String message = ex.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

    }
}
