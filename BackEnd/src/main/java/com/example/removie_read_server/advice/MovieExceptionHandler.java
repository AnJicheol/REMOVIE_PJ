package com.example.removie_read_server.advice;


import com.example.removie_read_server.errorResponse.MovieCodeErrorResponse;
import com.example.removie_read_server.exception.MovieCodeInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MovieCodeExceptionHandler {

    @ExceptionHandler(MovieInvalidException.class)
    public ResponseEntity<MovieCodeErrorResponse> handleMovieCodeInvalidException(MovieInvalidException ex){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(MovieCodeErrorResponse
                        .create(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }
}