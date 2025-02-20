package com.example.removie.kobis.exception;

public class ParsingResultEmptyException extends RuntimeException{
    public ParsingResultEmptyException(String message){
        super(message);
    }
    public ParsingResultEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
