package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class MovieManagerImpl implements MovieManager{
    private final MovieRepository movieRepository;

    @Autowired
    public MovieManagerImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDataEntity> getMovieEntity(List<String> movieCodeList){
       return movieRepository.findAllByMovieCodeIn(movieCodeList);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieDataEntity> getMovieEntity(String title){
        return movieRepository.findByTitle(title);

    }
}