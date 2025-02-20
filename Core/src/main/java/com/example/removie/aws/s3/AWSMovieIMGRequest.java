package com.example.removie.aws.s3;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


@Getter
public class AWSMovieIMGRequest {
    private final InputStream imgStream;
    private final ObjectMetadata imageMetaData;
    private final String key;


    public AWSMovieIMGRequest(ByteArrayInputStream imgStream, ObjectMetadata imageMetaData, String key) {
        this.imgStream = imgStream;
        this.imageMetaData = imageMetaData;
        this.key = key;
    }
}
