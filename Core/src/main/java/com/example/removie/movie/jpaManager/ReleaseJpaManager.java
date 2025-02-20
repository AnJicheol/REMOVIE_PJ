package com.example.removie.movie.jpaManager;


import com.example.removie.movie.entityMapper.ReleaseEntityMapper;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.repository.ReleaseRepository;
import com.example.removie.retry.IORetry;
import com.example.removie.version.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component
public class ReleaseJpaManager {
    private final ReleaseRepository releaseRepository;
    private final ReleaseEntityMapper releaseEntityMapper;
    private final VersionService versionService;

    @Autowired
    public ReleaseJpaManager(ReleaseRepository releaseRepository, ReleaseEntityMapper releaseEntityMapper, VersionService versionService){
        this.releaseRepository = releaseRepository;
        this.releaseEntityMapper = releaseEntityMapper;
        this.versionService = versionService;
    }

    @IORetry
    @Transactional
    public List<CurrentMovieVO> getCurrentMovieList(){
        return releaseRepository.findByVersion(versionService.getCurrentVersion());
    }

    @IORetry
    @Transactional
    public void saveCurrentMovieList(List<CurrentMovieVO> currentMovieVOList, Integer version){
        releaseRepository.saveAll(releaseEntityMapper.getReleaseEntityByVO(currentMovieVOList, version));
    }
}
