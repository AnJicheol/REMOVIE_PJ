package com.example.removie_read_server.errorResponse;

public class VersionErrorResponse extends ErrorResponse{

    public static VersionErrorResponse create(int status, String message){
        return new VersionErrorResponse(status, message);
    }

    public VersionErrorResponse(int status, String message) {
        super(status, message);
    }
}
