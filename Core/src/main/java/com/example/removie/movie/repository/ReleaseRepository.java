package com.example.removie.movie.repository;

import com.example.removie.movie.entity.ReleaseEntity;
import com.example.removie.movie.vo.CurrentMovieVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReleaseRepository extends JpaRepository <ReleaseEntity, Long> {

    @Query("SELECT new com.example.removie.movie.vo.CurrentMovieVO(r.movieCode, r.ranking) FROM ReleaseEntity r WHERE r.version = :version")
    List<CurrentMovieVO> findByVersion(@Param("version") Integer version);
}
