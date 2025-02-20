package com.example.removie_read_server.service.manager;

import com.example.removie_read_server.dto.RetireMovieProjection;
import com.example.removie_read_server.repository.RetireMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RetireMovieManagerImpl implements RetireMovieManager {
    private final RetireMovieRepository retireMovieRepository;

    @Autowired
    public RetireMovieManagerImpl(RetireMovieRepository retireMovieRepository) {
        this.retireMovieRepository = retireMovieRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RetireMovieProjection> getRetireMovieList(Integer version) {
        return retireMovieRepository.findAllByVersionGreaterThan(version);
    }
}
