package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.CinemaDTO;

public interface CinemaService {
    CinemaDTO getCinemaList(String movieCode);
}
