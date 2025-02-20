package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.ReleaseDateProjection;
import com.example.removie_read_server.repository.ReleaseDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReleaseDateServiceImpl implements ReleaseDateService{
    private final ReleaseDateRepository releaseDateRepository;

    @Autowired
    public ReleaseDateServiceImpl(ReleaseDateRepository releaseDateRepository) {
        this.releaseDateRepository = releaseDateRepository;
    }


    @Override
    @Cacheable(value = "moviePage", key = "#movieCode", sync = true)
    @Transactional(readOnly = true)
    public List<ReleaseDateProjection> getReleaseDate(String movieCode) {
        return releaseDateRepository.findByMovieCode(movieCode);
    }
}
