package com.example.registration.exceptions;

public class DuplicateGroupException extends RuntimeException{
    
    public DuplicateGroupException(String exception){
        super(exception);
    }
}