package com.example.removie_read_server.repository;


import com.example.removie_read_server.Entity.RetireMovieEntity;
import com.example.removie_read_server.dto.RetireMovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RetireMovieRepository extends JpaRepository<RetireMovieEntity, Long> {
    List<RetireMovieProjection> findAllByVersionGreaterThan(Integer version);
}
