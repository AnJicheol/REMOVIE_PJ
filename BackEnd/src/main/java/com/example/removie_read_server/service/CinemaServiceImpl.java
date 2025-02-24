package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.CinemaDTO;
import com.example.removie_read_server.errorCode.MovieErrorCode;
import com.example.removie_read_server.exception.MovieCodeInvalidException;
import com.example.removie_read_server.repository.CinemaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CinemaServiceImpl implements CinemaService{
    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    @Cacheable(value = "cinemaData", key = "#movieCode", sync = true)
    @Transactional(readOnly = true)
    public CinemaDTO getCinemaList(String movieCode){
        return cinemaRepository.findCinemaEntityByMovieCode(movieCode)
                .map(cinemaEntity -> new CinemaDTO(cinemaEntity.getCinemaDataList()))
                .orElseThrow(() -> new MovieInvalidException(MovieErrorCode.MOVIE_CODE_INVALID));
    }
}
