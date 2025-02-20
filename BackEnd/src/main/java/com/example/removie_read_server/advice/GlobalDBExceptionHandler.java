package com.example.removie_read_server.advice;

import com.example.removie_read_server.errorResponse.DBDataMissingErrorResponse;
import com.example.removie_read_server.exception.DBDataMissingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalDBExceptionHandler {

    @ExceptionHandler(DBDataMissingException.class)
    public ResponseEntity<DBDataMissingErrorResponse> handleDBDataMissingException(DBDataMissingException ex){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(DBDataMissingErrorResponse
                        .create(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
