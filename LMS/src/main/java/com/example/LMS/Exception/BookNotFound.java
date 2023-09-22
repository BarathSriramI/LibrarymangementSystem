package com.example.LMS.Exception;

public class BookNotFound extends RuntimeException{
    public BookNotFound(String message){
        super(message);
    }
}
