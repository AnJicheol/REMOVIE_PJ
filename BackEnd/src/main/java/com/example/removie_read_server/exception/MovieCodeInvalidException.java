package com.example.removie_read_server.exception;

import com.example.removie_read_server.errorCode.MovieErrorCode;

public class MovieCodeInvalidException extends RuntimeException{

    public MovieCodeInvalidException(MovieErrorCode movieErrorCode) {
        super(movieErrorCode.getMessage());
    }
}
