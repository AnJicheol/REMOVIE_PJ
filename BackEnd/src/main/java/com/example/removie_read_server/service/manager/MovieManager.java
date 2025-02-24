package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.Entity.MovieDataEntity;

import java.util.List;
import java.util.Optional;

public interface MovieManager {
    List<MovieDataEntity> getMovieEntity(List<String> movieCodeList);
    Optional<MovieDataEntity> getMovieEntity(String title);
}

