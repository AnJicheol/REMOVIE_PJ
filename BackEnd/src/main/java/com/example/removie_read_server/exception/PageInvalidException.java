package com.example.removie_read_server.exception;


import com.example.removie_read_server.errorCode.PageErrorCode;
import lombok.Getter;

@Getter
public class PageInvalidException extends RuntimeException{

    public PageInvalidException(PageErrorCode pageErrorCode) {
        super(pageErrorCode.getMessage());
    }
}
