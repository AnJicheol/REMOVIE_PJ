package com.example.removie.movie.entityMapper;

import com.example.removie.movie.entity.RetireMovieEntity;
import com.example.removie.movie.vo.RetireMovieVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RetireMovieEntityMapper {

    public List<RetireMovieEntity> getRetireMovieEntityByVO(List<RetireMovieVO> retireMovieVOList, Integer version){

        return retireMovieVOList.stream()
                .map(retireMovieVO -> new RetireMovieEntity(
                        retireMovieVO.getMovieCode(),
                        version
                ))
                .collect(Collectors.toList());
    }
}
