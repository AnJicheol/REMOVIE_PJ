package com.example.removie.movie.entityMapper;

import com.example.removie.movie.entity.NewMovieEntity;
import com.example.removie.movie.vo.NewMovieVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewMovieEntityMapper {

    public List<NewMovieEntity> getNewMovieListByVO(List<NewMovieVO> newMovieVOList, Integer version){
        return newMovieVOList.stream()
                .map(newMovieVO -> new NewMovieEntity(
                        version,
                        newMovieVO.getRanking(),
                        newMovieVO.getMovieCode()
                ))
                .collect(Collectors.toList());
    }
}
