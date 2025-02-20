package com.example.removie_read_server.exception;



public class ListSizeExceededException extends RuntimeException{
    public ListSizeExceededException(String message) {
        super(message);
    }
}
