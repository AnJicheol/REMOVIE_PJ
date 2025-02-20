package com.example.removie.movie.jpaManager;

import com.example.removie.movie.entityMapper.ReleaseDateEntityMapper;
import com.example.removie.movie.repository.ReleaseMovieDateRepository;
import com.example.removie.movie.vo.ReleaseDate;
import com.example.removie.retry.IORetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ReleaseDateJpaManager {
    private final ReleaseMovieDateRepository releaseMovieDateRepository;
    private final ReleaseDateEntityMapper releaseDateEntityMapper;

    @Autowired
    public ReleaseDateJpaManager(ReleaseMovieDateRepository releaseMovieDateRepository, ReleaseDateEntityMapper releaseDateEntityMapper) {
        this.releaseMovieDateRepository = releaseMovieDateRepository;
        this.releaseDateEntityMapper = releaseDateEntityMapper;
    }

    @IORetry
    @Transactional
    public void saveReleaseDate(List<ReleaseDate> releaseDateList){
        releaseMovieDateRepository.saveAll(releaseDateEntityMapper.getReleaseDateEntityByVO(releaseDateList));
    }
}
