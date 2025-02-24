package com.example.removie_read_server.mapper;


import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.dto.MovieDataResponse;
import com.example.removie_read_server.enums.AWS;

import java.util.List;
import java.util.stream.Collectors;

public class MovieDataMapper {

    public static List<MovieDataResponse> getMovieDataResponse(List<MovieDataEntity> movieDataEntityList){
        return movieDataEntityList.stream()
                .map(movieDataEntity -> MovieDataResponse.builder()
                        .info(movieDataEntity.getInfo())
                        .title(movieDataEntity.getTitle())
                        .movieCode(movieDataEntity.getMovieCode())
                        .posterIMG(AWS.AWS_S3_URL.getValue() + movieDataEntity.getMovieCode())
                        .build())
                .collect(Collectors.toList());

    }
    
    public static MovieDataResponse getMovieDataResponse(MovieDataEntity movieDataEntity){
        return  MovieDataResponse.builder()
                        .info(movieDataEntity.getInfo())
                        .title(movieDataEntity.getTitle())
                        .movieCode(movieDataEntity.getMovieCode())
                        .posterIMG(AWS.AWS_S3_URL.getValue() + movieDataEntity.getMovieCode())
                        .build();
    }
}
