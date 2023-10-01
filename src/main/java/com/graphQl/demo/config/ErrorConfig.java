package com.graphQl.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice
public class ErrorConfig {
    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail problemDetail(HttpStatus status, RuntimeException exception){
        return ProblemDetail
                .forStatusAndDetail(status, exception.getMessage());
    }
}
