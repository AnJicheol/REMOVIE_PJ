package com.example.removie_read_server.advice;


import com.example.removie_read_server.errorResponse.PageErrorResponse;
import com.example.removie_read_server.exception.PageInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PageExceptionHandler {

    @ExceptionHandler(PageInvalidException.class)
    public ResponseEntity<PageErrorResponse> handlePageInvalidException(PageInvalidException e){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(PageErrorResponse
                        .create(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }
}
