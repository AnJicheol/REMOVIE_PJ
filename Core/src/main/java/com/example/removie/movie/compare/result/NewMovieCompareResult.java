package com.example.removie.movie.compare.result;

import com.example.removie.movie.vo.MovieData;
import com.example.removie.movie.vo.NewMovieVO;
import com.example.removie.movie.vo.ReleaseDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.List;


@Getter
@NoArgsConstructor
public class NewMovieCompareResult{
    private List<NewMovieVO> newMovieList;
    private List<MovieData> newMovieDataList;
    private List<ReleaseDate> releaseDateList;

    @Builder
    public NewMovieCompareResult(List<NewMovieVO> newMovieList, List<MovieData> newMovieDataList, List<ReleaseDate> releaseDateList){
        this.newMovieList = newMovieList;
        this.newMovieDataList = newMovieDataList;
        this.releaseDateList = releaseDateList;
    }

    public boolean isEmpty(){
        return newMovieList.isEmpty()
                && newMovieDataList.isEmpty()
                && releaseDateList.isEmpty();
    }
}
