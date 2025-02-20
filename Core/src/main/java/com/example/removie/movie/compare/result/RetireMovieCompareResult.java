package com.example.removie.movie.compare.result;

import com.example.removie.movie.vo.RetireMovieVO;
import lombok.Getter;

import java.util.List;

@Getter
public class RetireMovieCompareResult {
    private final List<RetireMovieVO> retireMovieVOList;

    public RetireMovieCompareResult(List<RetireMovieVO> retireMovieVOList) {
        this.retireMovieVOList = retireMovieVOList;
    }

    public boolean isEmpty(){
        return retireMovieVOList.isEmpty();
    }
}
