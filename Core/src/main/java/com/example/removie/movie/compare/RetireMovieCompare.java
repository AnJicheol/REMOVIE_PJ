package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.compare.result.RetireMovieCompareResult;

import java.util.List;

public interface RetireMovieCompare {
     RetireMovieCompareResult compare(NewReleaseMap newReleaseMap, List<CurrentMovieVO> currentMovieVOList);
}
