package com.example.removie_read_server.errorResponse;

public class DBDataMissingErrorResponse extends ErrorResponse{
    public static DBDataMissingErrorResponse create(int status, String message){
        return new DBDataMissingErrorResponse(status, message);
    }

    public DBDataMissingErrorResponse(int status, String message) {
        super(status, message);
    }
}
