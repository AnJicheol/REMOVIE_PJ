package com.example.removie_read_server.repository;

import com.example.removie_read_server.Entity.NewMovieEntity;
import com.example.removie_read_server.dto.NewMovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewMovieRepository extends JpaRepository<NewMovieEntity, Long> {
    List<NewMovieProjection> findAllByVersionGreaterThan(Integer version);
}
