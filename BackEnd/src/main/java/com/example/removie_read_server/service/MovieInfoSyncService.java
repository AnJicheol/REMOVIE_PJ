package com.example.removie_read_server.service;


import com.example.removie_read_server.dto.MovieSyncDTO;

public interface MovieInfoSyncService {
    MovieSyncDTO getMovieSyneData(Integer version);
}
