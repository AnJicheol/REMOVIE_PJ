package com.example.removie.movie.repository;

import com.example.removie.movie.entity.NewMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewMovieRepository extends JpaRepository<NewMovieEntity, Long> {
}
