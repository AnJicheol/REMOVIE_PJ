package com.example.removie.movie.repository;

import com.example.removie.movie.entity.ReleaseDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseMovieDateRepository extends JpaRepository<ReleaseDateEntity, Long> {
}
