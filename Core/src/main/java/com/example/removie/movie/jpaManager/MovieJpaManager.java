package com.example.removie.movie.jpaManager;


import com.example.removie.movie.entityMapper.MovieDataEntityMapper;
import com.example.removie.movie.vo.MovieData;
import com.example.removie.movie.repository.MovieRepository;
import com.example.removie.retry.IORetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MovieJpaManager {
    private final MovieRepository movieRepository;
    private final MovieDataEntityMapper movieDataEntityMapper;


    @Autowired
    public MovieJpaManager(MovieRepository movieRepository, MovieDataEntityMapper movieDataEntityMapper) {
        this.movieRepository = movieRepository;
        this.movieDataEntityMapper = movieDataEntityMapper;
    }

    @IORetry
    @Transactional
    public void saveAllMovieData(List<MovieData> movieDataList){
        movieRepository.saveAll(movieDataEntityMapper.getMovieDataEntityListByVO(movieDataList));
    }

}
