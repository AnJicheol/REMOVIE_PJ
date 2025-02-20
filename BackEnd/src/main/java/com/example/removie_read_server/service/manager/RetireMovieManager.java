package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.dto.RetireMovieProjection;

import java.util.List;


public interface RetireMovieManager {
    List<RetireMovieProjection> getRetireMovieList(Integer version);
}
