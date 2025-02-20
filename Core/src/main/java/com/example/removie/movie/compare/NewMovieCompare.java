package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.compare.result.NewMovieCompareResult;

import java.util.List;

public interface NewMovieCompare {
    NewMovieCompareResult compare(NewReleaseMap newReleaseMap, List<CurrentMovieVO> currentMovieVOList);
}
