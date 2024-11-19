package com.example.demo.exceptions;

public class PersonDoesNotExistException extends RuntimeException{
    public PersonDoesNotExistException(String message) {
        super(message);
    }
}
