package com.example.demo.infrastructure.exceptions;




public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}

