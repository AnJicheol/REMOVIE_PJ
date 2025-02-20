package com.example.removie.update;

import com.example.removie.cinema.CinemaEntity;
import com.example.removie.cinema.service.CinemaRedisService;
import com.example.removie.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 영화관 프로세스에 파사드 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class CinemaUpdateServiceImpl implements CinemaUpdateService{
    private final CinemaService cinemaService;
    private final CinemaRedisService cinemaRedisService;
    private final CompareTargetProvider compareTargetProvider;

    @Autowired
    public CinemaUpdateServiceImpl(CinemaService cinemaService, CinemaRedisService cinemaRedisService, CompareTargetProvider compareTargetProvider) {
        this.cinemaService = cinemaService;
        this.cinemaRedisService = cinemaRedisService;
        this.compareTargetProvider = compareTargetProvider;
    }

    public void cinemaDataUpdate(){
        List<CinemaEntity> cinemaEntityList = cinemaService.getCinemaEntityList(getMovieCodeList());
        update(cinemaEntityList);
    }

    private void update(List<CinemaEntity> cinemaEntityList){
        cinemaRedisService.saveCurrentCinema(cinemaEntityList);
    }

    private List<String> getMovieCodeList(){
        return compareTargetProvider.getParsingResult().getMovieCodeList();
    }
}
