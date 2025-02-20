package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.NewMovieResponse;

import java.util.List;

public interface NewMovieService {
    List<NewMovieResponse> getNewMovieList(Integer version);
}
