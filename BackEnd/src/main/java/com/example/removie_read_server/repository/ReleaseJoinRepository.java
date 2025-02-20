package com.example.removie_read_server.repository;


import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.Entity.ReleaseJoinEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseJoinRepository extends JpaRepository <ReleaseJoinEntity, Long> {

    @Query("""
    SELECT m
    FROM ReleaseJoinEntity r JOIN r.movieDataEntity m
    WHERE r.version = (
        SELECT MAX(rv.version) FROM ReleaseJoinEntity rv)
    ORDER BY r.ranking ASC
""")
    List<MovieDataEntity> findAllMovieInfoByPage(Pageable pageable);
}
