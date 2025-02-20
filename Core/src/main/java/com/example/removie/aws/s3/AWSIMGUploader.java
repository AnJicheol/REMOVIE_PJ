package com.example.removie.aws.s3;


public interface AWSIMGUploader<T>{
    String uploadIMG(T t);
}
