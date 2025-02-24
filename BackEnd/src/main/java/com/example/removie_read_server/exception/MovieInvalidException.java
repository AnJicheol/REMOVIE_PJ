package com.example.removie_read_server.exception;

import com.example.removie_read_server.errorCode.MovieErrorCode;

public class MovieInvalidException extends RuntimeException{

    public MovieInvalidException(MovieErrorCode movieErrorCode) {
        super(movieErrorCode.getMessage());
    }
}