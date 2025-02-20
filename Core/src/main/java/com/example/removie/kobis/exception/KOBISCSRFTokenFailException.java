package com.example.removie.kobis.exception;

public class KOBISCSRFTokenFailException extends RuntimeException{
    public KOBISCSRFTokenFailException(String message){
        super(message);
    }
    public KOBISCSRFTokenFailException(String message, Throwable cause) {
        super(message, cause);
    }
}
