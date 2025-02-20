package com.example.removie.movie.entityMapper;

import com.example.removie.movie.entity.MovieDataEntity;
import com.example.removie.movie.vo.MovieData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieDataEntityMapper {

    public List<MovieDataEntity> getMovieDataEntityListByVO(List<MovieData> movieDataList) {
        return movieDataList.stream()
                .map(movieData -> new MovieDataEntity(
                        movieData.getTitle(),
                        movieData.getMovieCode(),
                        movieData.getPosterIMG(),
                        movieData.getInfo()
                ))
                .collect(Collectors.toList());
    }
}
