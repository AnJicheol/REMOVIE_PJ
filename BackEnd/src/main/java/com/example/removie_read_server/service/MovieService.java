package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.MovieDataResponse;


import java.util.List;

public interface MovieService {
    List<MovieDataResponse> getMovieInfoList(List<String> movieCodeList);
    List<MovieDataResponse> getMovieInfoList(int offset, int limit);
}
