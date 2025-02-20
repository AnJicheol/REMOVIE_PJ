package com.example.removie.movie.jpaManager;

import com.example.removie.movie.entity.ReMovieEntity;
import com.example.removie.movie.repository.ReReleaseRepository;
import com.example.removie.retry.IORetry;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ReMovieJpaManager {
    private final ReReleaseRepository reReleaseRepository;

    @Autowired
    public ReMovieJpaManager(ReReleaseRepository reReleaseRepository) {
        this.reReleaseRepository = reReleaseRepository;
    }

    @IORetry
    @Transactional(readOnly = true)
    public boolean existsByMovieCode(@NotBlank String movieCode){
        return reReleaseRepository.existsByMovieCode(movieCode);
    }

    @Transactional
    public void saveAll(List<ReMovieEntity> releaseEntityList){
        reReleaseRepository.saveAll(releaseEntityList);
    }

}
