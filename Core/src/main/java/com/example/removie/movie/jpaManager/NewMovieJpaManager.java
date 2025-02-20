package com.example.removie.movie.jpaManager;


import com.example.removie.movie.entityMapper.NewMovieEntityMapper;
import com.example.removie.movie.vo.NewMovieVO;
import com.example.removie.movie.repository.NewMovieRepository;
import com.example.removie.retry.IORetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class NewMovieJpaManager {
    private final NewMovieRepository newMovieRepository;
    private final NewMovieEntityMapper newMovieEntityMapper;

    @Autowired
    public NewMovieJpaManager(NewMovieRepository newMovieRepository, NewMovieEntityMapper newMovieEntityMapper) {
        this.newMovieRepository = newMovieRepository;
        this.newMovieEntityMapper = newMovieEntityMapper;
    }

    @IORetry
    @Transactional
    public void saveAllNewMovieVO(List<NewMovieVO> newMovieVOList, Integer version){
        newMovieRepository.saveAll(newMovieEntityMapper.getNewMovieListByVO(newMovieVOList, version));
    }
}
