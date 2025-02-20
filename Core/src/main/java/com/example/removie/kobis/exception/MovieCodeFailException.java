package com.example.removie.kobis.exception;

public class MovieCodeFailException extends RuntimeException{
    public MovieCodeFailException(String message){
        super(message);
    }
    public MovieCodeFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
