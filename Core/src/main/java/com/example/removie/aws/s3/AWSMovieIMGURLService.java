package com.example.removie.aws.s3;

public interface AWSMovieIMGURLService {
    String uploadMovieImageAndReturnURL(String movieCode);
}
