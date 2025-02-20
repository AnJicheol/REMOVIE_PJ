package com.example.removie_read_server.exception;

public class DBDataMissingException extends RuntimeException{
    public DBDataMissingException(String message) {
        super(message);
    }
}
