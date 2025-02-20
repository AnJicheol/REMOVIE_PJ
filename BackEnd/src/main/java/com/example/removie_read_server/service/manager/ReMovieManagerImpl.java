package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ReMovieManagerImpl implements ReMovieManager {
    private final ReleaseRepository releaseRepository;

    @Autowired
    public ReMovieManagerImpl(ReleaseRepository releaseRepository) {
        this.releaseRepository = releaseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<String> latestRemovieSet() {
        return new HashSet<>(latestRemovie());

    }

    @Override
    @Transactional(readOnly = true)
    public List<String> latestRemovie(){
        return releaseRepository.findLatestRemovie();
    }
}
