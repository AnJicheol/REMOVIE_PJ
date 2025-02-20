package com.example.removie.movie.entityMapper;

import com.example.removie.movie.entity.ReleaseDateEntity;
import com.example.removie.movie.vo.ReleaseDate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReleaseDateEntityMapper {
    public List<ReleaseDateEntity> getReleaseDateEntityByVO(List<ReleaseDate> releaseDateList) {
        return releaseDateList.stream()
                .flatMap(releaseDate -> releaseDate.getReleaseDate().stream()
                        .map(date -> new ReleaseDateEntity(
                                releaseDate.getMovieCode(),
                                date
                        )))
                .collect(Collectors.toList());
    }
}
