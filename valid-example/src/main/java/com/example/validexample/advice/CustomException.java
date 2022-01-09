package com.example.validexample.advice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.httpStatus = httpStatus;
    }

    public CustomException(String message , HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

//    @Override
//    public synchronized Throwable fillInStackTrace() {
//        return this;
//    }
}
