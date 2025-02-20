package com.example.removie.kobis.exception;

public class MovieGroupFailException extends RuntimeException{
    public MovieGroupFailException(String message){
        super(message);
    }
    public MovieGroupFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
