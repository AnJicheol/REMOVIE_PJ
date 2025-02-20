package com.example.removie.cinema.service;

import com.example.removie.cinema.CinemaEntity;
import com.example.removie.cinema.repository.CurrentCinemaRepository;
import com.example.removie.retry.IORetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *  레디스 RedisRepository 와 통신하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Service
public class CinemaRedisServiceImpl implements CinemaRedisService{
    private final CurrentCinemaRepository cinemaRepository;

    @Autowired
    public CinemaRedisServiceImpl(CurrentCinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    @IORetry
    public void saveCurrentCinema(List<CinemaEntity> cinemaEntityList) {
        cinemaRepository.saveAll(cinemaEntityList);

    }

    @Override
    @IORetry
    public void deleteRetireMovie(List<String> movieCodeList) {
        cinemaRepository.deleteAllById(movieCodeList);
    }

    @Override
    @IORetry
    public Iterable<CinemaEntity> getCinemaListByMovieCode(List<String> movieCodeList){
        return cinemaRepository.findAllById(movieCodeList);
    }

}
