package com.example.removie.version.repository;

import com.example.removie.movie.entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, Long> {


    @Query("SELECT MAX(e.version) FROM VersionEntity e")
    Optional<Integer> findMaxVersion();

}
