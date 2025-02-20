package com.example.removie_read_server.errorResponse;

public class SizeErrorResponse extends ErrorResponse{

    public static SizeErrorResponse create(int status, String message){
        return new SizeErrorResponse(status, message);
    }

    public SizeErrorResponse(int status, String message) {
        super(status, message);
    }
}
