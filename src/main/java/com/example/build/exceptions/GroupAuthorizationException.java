package com.example.build.exceptions;

public class GroupAuthorizationException extends RuntimeException{
    
    public GroupAuthorizationException(String exception){
        super(exception);
    }
}