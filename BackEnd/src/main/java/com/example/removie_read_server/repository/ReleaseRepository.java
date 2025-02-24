package com.example.removie_read_server.repository;

import com.example.removie_read_server.Entity.ReleaseEntity;
import com.example.removie_read_server.dto.ReleaseProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReleaseRepository extends JpaRepository<ReleaseEntity, Long> {

    @Query("""
        SELECT r
        FROM ReleaseEntity r
        WHERE r.version = (
            SELECT MAX(rv.version) FROM ReleaseEntity rv
        )
    """)
    List<ReleaseProjection> findAllByLatestVersion();

    List<ReleaseProjection> findAllByVersion(Integer version);

    @Query("SELECT r.movieCode FROM ReleaseEntity r WHERE r.version = (SELECT MAX(r2.version) FROM ReleaseEntity r2) " +
            "AND r.movieCode IN (SELECT rm.movieCode FROM ReMovieEntity rm)")
    List<String> findLatestRemovie();


     @Query("""
    SELECT m
    FROM ReleaseEntity r
    JOIN MovieDataEntity m ON r.movieCode = m.movieCode
    WHERE r.version = (
        SELECT MAX(rv.version) FROM ReleaseEntity rv
    )
    """)
    List<MovieDataEntity> findAllMovieInfoByPage(Pageable pageable);
}
