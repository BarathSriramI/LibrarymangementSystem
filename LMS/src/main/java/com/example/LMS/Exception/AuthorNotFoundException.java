package com.example.LMS.Exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message)
    {
        super(message);
    }
}
