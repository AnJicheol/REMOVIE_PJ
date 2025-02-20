package com.example.removie.movie.jpaManager;

import com.example.removie.movie.entity.RetireMovieEntity;
import com.example.removie.movie.entityMapper.RetireMovieEntityMapper;
import com.example.removie.movie.repository.RetireMovieRepository;
import com.example.removie.movie.vo.RetireMovieVO;
import com.example.removie.retry.IORetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class RetireMovieJpaManager {
    private final RetireMovieEntityMapper retireMovieEntityMapper;
    private final RetireMovieRepository retireMovieRepository;


    @Autowired
    public RetireMovieJpaManager(RetireMovieEntityMapper retireMovieEntityMapper, RetireMovieRepository retireMovieRepository) {
        this.retireMovieEntityMapper = retireMovieEntityMapper;
        this.retireMovieRepository = retireMovieRepository;
    }


    @IORetry
    @Transactional
    public void saveRetireMovie(List<RetireMovieVO> retireMovieVOList, Integer version){
        List<RetireMovieEntity> retireMovieEntities = retireMovieEntityMapper.getRetireMovieEntityByVO(retireMovieVOList, version);
        retireMovieRepository.saveAll(retireMovieEntities);
    }
}
