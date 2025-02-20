package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.ReleaseDateProjection;

import java.util.List;

public interface ReleaseDateService {
    List<ReleaseDateProjection> getReleaseDate(String movieCode);
}
