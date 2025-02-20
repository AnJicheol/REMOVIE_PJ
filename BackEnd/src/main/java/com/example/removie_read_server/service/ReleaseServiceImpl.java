package com.example.removie_read_server.service;


import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.dto.ReleaseDTO;
import com.example.removie_read_server.dto.ReleaseProjection;
import com.example.removie_read_server.repository.ReleaseJoinRepository;
import com.example.removie_read_server.repository.ReleaseRepository;
import com.example.removie_read_server.service.manager.ReMovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ReleaseServiceImpl implements ReleaseService{
    private final ReleaseRepository releaseRepository;
    private final ReleaseJoinRepository releaseJoinRepository;
    private final ReMovieManager reMovieManager;

    @Autowired
    public ReleaseServiceImpl(ReleaseRepository releaseRepository, ReleaseJoinRepository releaseJoinRepository, ReMovieManager reMovieManager) {
        this.releaseRepository = releaseRepository;
        this.releaseJoinRepository = releaseJoinRepository;
        this.reMovieManager = reMovieManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseProjection> getReleaseInfo() {
        return releaseRepository.findAllByLatestVersion();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReleaseProjection> getReleaseInfoByVersion(Integer version){
        return releaseRepository.findAllByVersion(version);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDataEntity> getMovieDataByReleasePage(Pageable pageable) {
        return releaseJoinRepository.findAllMovieInfoByPage(pageable);
    }

    @Override
    @Cacheable(value = "moviePage", sync = true)
    @Transactional(readOnly = true)
    public List<ReleaseDTO> getReleaseDTOList(){

        List<ReleaseProjection> list = getReleaseInfo();
        Set<String> removieList = reMovieManager.latestRemovieSet();

        ReleaseDTO[] dtoList = new ReleaseDTO[list.size()];

        for(ReleaseProjection rp : list){
            dtoList[rp.getRanking() - 1] = ReleaseDTO.builder()
                    .movieCode(rp.getMovieCode())
                    .removie(removieList.contains(rp.getMovieCode()))
                    .build();
        }
        return new ArrayList<>(Arrays.asList(dtoList));
    }
}
