package com.example.removie_read_server.repository;

import com.example.removie_read_server.Entity.CinemaEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CinemaRepository extends CrudRepository<CinemaEntity, String> {
    Optional<CinemaEntity> findCinemaEntityByMovieCode(String movieCode);
}
