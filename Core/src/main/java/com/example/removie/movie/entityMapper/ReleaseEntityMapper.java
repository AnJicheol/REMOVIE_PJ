package com.example.removie.movie.entityMapper;

import com.example.removie.movie.entity.ReleaseEntity;
import com.example.removie.movie.vo.CurrentMovieVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReleaseEntityMapper {
    public List<ReleaseEntity> getReleaseEntityByVO(List<CurrentMovieVO> currentMovieVOList, Integer version){
        return currentMovieVOList.stream()
                .map(currentMovieVO -> ReleaseEntity.builder()
                        .ranking(currentMovieVO.getRanking())
                        .movieCode(currentMovieVO.getMovieCode())
                        .version(version)
                        .build())
                .collect(Collectors.toList());
    }
}
