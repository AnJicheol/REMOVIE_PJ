package com.example.removie_read_server.errorResponse;

public class PageErrorResponse extends ErrorResponse{

    public static PageErrorResponse create(int status, String message){
        return new PageErrorResponse(status, message);
    }

    public PageErrorResponse(int status, String message) {
        super(status, message);
    }
}
