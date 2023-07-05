package com.example.demo.infrastructure.exceptions;

public class DataHasNotChangedException extends RuntimeException {

    public DataHasNotChangedException(String message) {
        super(message);
    }
}