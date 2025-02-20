package com.example.removie_read_server.errorResponse;

public class MovieCodeErrorResponse extends ErrorResponse{

    public static MovieCodeErrorResponse create(int status, String message){
        return new MovieCodeErrorResponse(status, message);
    }
    public MovieCodeErrorResponse(int status, String message) {
        super(status, message);
    }
}
