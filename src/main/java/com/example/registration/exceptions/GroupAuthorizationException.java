package com.example.registration.exceptions;

public class GroupAuthorizationException extends RuntimeException{
    
    public GroupAuthorizationException(String exception){
        super(exception);
    }
}