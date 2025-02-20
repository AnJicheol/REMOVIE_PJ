package com.example.removie_read_server.service;

import com.example.removie_read_server.dto.NewMovieProjection;
import com.example.removie_read_server.dto.NewMovieResponse;
import com.example.removie_read_server.repository.NewMovieRepository;
import com.example.removie_read_server.service.manager.ReMovieManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewMovieServiceImpl implements NewMovieService{
    private final NewMovieRepository newMovieRepository;
    private final ReMovieManager reMovieManager;

    @Autowired
    public NewMovieServiceImpl(NewMovieRepository newMovieRepository, ReMovieManager reMovieManager) {
        this.newMovieRepository = newMovieRepository;
        this.reMovieManager = reMovieManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NewMovieResponse> getNewMovieList(Integer version){
        List<NewMovieProjection> newMovieList = newMovieRepository.findAllByVersionGreaterThan(version);

        Set<String> removieList = reMovieManager.latestRemovieSet();

        return newMovieList.stream()
                .map(newMovie -> NewMovieResponse.builder()
                        .movieCode(newMovie.getMovieCode())
                        .ranking(newMovie.getRanking())
                        .removie(removieList.contains(newMovie.getMovieCode()))
                        .build())
                .collect(Collectors.toList());

    }

}
