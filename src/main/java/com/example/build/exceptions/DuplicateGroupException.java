package com.example.build.exceptions;

public class DuplicateGroupException extends RuntimeException{
    
    public DuplicateGroupException(String exception){
        super(exception);
    }
}