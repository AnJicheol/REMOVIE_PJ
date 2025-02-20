package com.example.removie_read_server.advice;

import com.example.removie_read_server.errorResponse.SizeErrorResponse;
import com.example.removie_read_server.exception.ListSizeExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SizeExceptionHandler {

    @ExceptionHandler(ListSizeExceededException.class)
    public ResponseEntity<SizeErrorResponse> handleSizeException(ListSizeExceededException ex){
        return ResponseEntity
                .status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body(SizeErrorResponse
                        .create(HttpStatus.PAYLOAD_TOO_LARGE.value(), ex.getMessage()));
    }
}
