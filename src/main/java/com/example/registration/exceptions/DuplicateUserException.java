package com.example.registration.exceptions;

public class DuplicateUserException extends RuntimeException{
    
    public DuplicateUserException(String exception){
        super(exception);
    }
}
