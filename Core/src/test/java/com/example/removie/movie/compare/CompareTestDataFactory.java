package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.vo.BasicMovieVO;
import com.example.removie.movie.vo.CurrentMovieVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class CompareTestDataFactory {

    static NewReleaseMap createNewReleaseMap() {
        Map<String, BasicMovieVO> movieMap = new HashMap<>();
        movieMap.put("MOV001", BasicMovieVO.builder().movieCode("MOV001").ranking(1).build());
        movieMap.put("MOV002", BasicMovieVO.builder().movieCode("MOV002").ranking(2).build());
        movieMap.put("MOV003", BasicMovieVO.builder().movieCode("MOV003").ranking(5).build());
        movieMap.put("MOV004", BasicMovieVO.builder().movieCode("MOV004").ranking(4).build());
        movieMap.put("MOV005", BasicMovieVO.builder().movieCode("MOV005").ranking(3).build());
        return new NewReleaseMap(movieMap);
    }

    static List<CurrentMovieVO> createCurrentMovieVOList() {
        return List.of(
                new CurrentMovieVO("MOV001", 1),
                new CurrentMovieVO("MOV003", 3),
                new CurrentMovieVO("MOV005", 5),
                new CurrentMovieVO("MOV006", 6),
                new CurrentMovieVO("MOV007", 7)
        );
    }

}
