package com.example.removie_read_server.service;

import com.example.removie_read_server.Entity.MovieDataEntity;
import com.example.removie_read_server.dto.MovieDataResponse;
import com.example.removie_read_server.errorCode.MovieErrorCode;
import com.example.removie_read_server.exception.ListSizeExceededException;
import com.example.removie_read_server.exception.MovieCodeInvalidException;
import com.example.removie_read_server.mapper.MovieDataMapper;
import com.example.removie_read_server.errorCode.PageErrorCode;
import com.example.removie_read_server.exception.PageInvalidException;
import com.example.removie_read_server.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MovieServiceImpl implements MovieService{
    private final static int SUPPORTED_SEARCH_SIZE = 300;
    private final static String PAGE_SORT_PIVOT = "Ranking";

    private final MovieRepository movieRepository;
    private final ReleaseService releaseService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,  ReleaseService releaseService) {
        this.movieRepository = movieRepository;
        this.releaseService = releaseService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieDataResponse> getMovieInfoList(List<String> movieCodeList) {
        validateListSize(movieCodeList);
        List<MovieDataEntity> movieDataEntityList = movieRepository.findAllByMovieCodeIn(movieCodeList);
        validateMovieDataEntity(movieDataEntityList);

        return createResponse(movieDataEntityList);
    }

    @Override
    @Cacheable(value = "moviePage", key = "#offset + '-' + #limit", sync = true)
    @Transactional(readOnly = true)
    public List<MovieDataResponse> getMovieInfoList(int offset, int limit){
        List<MovieDataEntity> movieDataEntityList = releaseService.getMovieDataByReleasePage(validatePage(offset, limit));
        return createResponse(movieDataEntityList);
    }

    private void validateListSize(List<String> movieCodeList){
        if(movieCodeList.size() >= SUPPORTED_SEARCH_SIZE){
            throw new ListSizeExceededException(MovieErrorCode.MOVIE_CODE_SIZE_LARGE.getMessage());
        }
    }

    private void validateMovieDataEntity(List<MovieDataEntity> movieDataEntityList){
        if(movieDataEntityList.isEmpty()){
            throw new MovieCodeInvalidException(MovieErrorCode.MOVIE_CODE_INVALID);
        }
    }

    private Pageable validatePage(int offset, int limit){
        if (offset > limit) throw new PageInvalidException(PageErrorCode.PAGE_RANGE_INVALID);
        return PageRequest.of(offset, limit, Sort.by(PAGE_SORT_PIVOT).ascending());
    }

    private List<MovieDataResponse> createResponse(List<MovieDataEntity> movieDataEntityList) {
        return MovieDataMapper.getMovieDataResponse(
                movieDataEntityList);
    }

}
