package com.example.demo.exceptions;

public class UserAlreadyHasAddressException extends RuntimeException{
    public UserAlreadyHasAddressException(String message) {
        super(message);
    }
}
