package com.example.removie.movie.repository;

import com.example.removie.movie.entity.ReMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReReleaseRepository extends JpaRepository<ReMovieEntity, Long> {
    boolean existsByMovieCode(String movieCode);
}
