package com.example.demo.exceptions;

public class AddressNotLinkedException extends RuntimeException{
    public AddressNotLinkedException(String message) {
        super(message);
    }
}
