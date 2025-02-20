package com.example.removie_read_server.repository;

import com.example.removie_read_server.Entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VersionRepository extends JpaRepository<VersionEntity, Long> {
    Optional<VersionEntity> findTopByOrderByVersionDesc();
}
