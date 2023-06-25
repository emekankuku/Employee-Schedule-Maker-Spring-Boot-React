package com.example.build.exceptions;

public class DuplicateUserException extends RuntimeException{
    
    public DuplicateUserException(String exception){
        super(exception);
    }
}
