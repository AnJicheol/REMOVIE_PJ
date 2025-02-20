package com.example.removie_read_server.repository;

import com.example.removie_read_server.Entity.ReleaseDateEntity;
import com.example.removie_read_server.dto.ReleaseDateProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ReleaseDateRepository extends JpaRepository<ReleaseDateEntity, Long> {
    List<ReleaseDateProjection> findByMovieCode(String movieCode);
}
