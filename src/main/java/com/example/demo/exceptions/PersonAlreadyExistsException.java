package com.example.demo.exceptions;

public class PersonAlreadyExistsException extends RuntimeException{
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
