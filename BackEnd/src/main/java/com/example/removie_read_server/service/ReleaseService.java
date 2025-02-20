package com.example.removie_read_server.service;


import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.dto.ReleaseDTO;
import com.example.removie_read_server.dto.ReleaseProjection;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReleaseService {
    List<ReleaseProjection> getReleaseInfo();
    List<MovieDataEntity> getMovieDataByReleasePage(Pageable pageable);
    List<ReleaseProjection> getReleaseInfoByVersion(Integer version);
    List<ReleaseDTO> getReleaseDTOList();
}
