package com.example.removie.kobis.exception;

public class KOBISTitleParsingFailException extends RuntimeException{
    public KOBISTitleParsingFailException(String message){
        super(message);
    }
    public KOBISTitleParsingFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
