package com.example.removie.kobis.exception;


public class DocIOException extends RuntimeException {
    public DocIOException(String message){
        super(message);
    }
    public DocIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
