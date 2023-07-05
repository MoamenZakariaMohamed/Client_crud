package com.example.demo.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public  class BaseCustomException extends RuntimeException {

    public BaseCustomException(String message) {
        super(message);
    }


}
