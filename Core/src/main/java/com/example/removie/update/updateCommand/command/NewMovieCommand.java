package com.example.removie.update.updateCommand.command;


import com.example.removie.movie.entity.ReMovieEntity;
import com.example.removie.movie.jpaManager.MovieJpaManager;
import com.example.removie.movie.jpaManager.ReMovieJpaManager;
import com.example.removie.movie.jpaManager.ReleaseDateJpaManager;
import com.example.removie.movie.vo.MovieData;
import com.example.removie.movie.compare.result.NewMovieCompareResult;
import com.example.removie.movie.jpaManager.NewMovieJpaManager;
import com.example.removie.movie.vo.NewMovieVO;
import com.example.removie.movie.vo.ReleaseDate;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class NewMovieCommand implements ChangeCommand{
    private final NewMovieJpaManager newMovieJpaManager;
    private final MovieJpaManager movieJpaManager;
    private final ReleaseDateJpaManager releaseDateJpaManager;
    private final NewMovieCompareResult newMovieCompareResult;
    private final ReMovieJpaManager reMovieJpaManager;

    @Builder
    public NewMovieCommand(NewMovieJpaManager newMovieJpaManager, MovieJpaManager movieJpaManager, NewMovieCompareResult newMovieCompareResult, ReleaseDateJpaManager releaseDateJpaManager, ReMovieJpaManager reMovieJpaManager) {
        this.newMovieJpaManager = newMovieJpaManager;
        this.movieJpaManager = movieJpaManager;
        this.newMovieCompareResult = newMovieCompareResult;
        this.releaseDateJpaManager = releaseDateJpaManager;
        this.reMovieJpaManager = reMovieJpaManager;
    }


    @Override
    public void update(Integer version) {

        saveNewMovieList(newMovieCompareResult.getNewMovieList(), version);
        saveNewMovieDataList(newMovieCompareResult.getNewMovieDataList());
        saveNewMovieDateList(newMovieCompareResult.getReleaseDateList());
        saveReMovieList(newMovieCompareResult.getReleaseDateList());
    }

    @Override
    public boolean isEmpty() {
        return newMovieCompareResult.isEmpty();
    }

    private void saveNewMovieList(List<NewMovieVO> newMovieVOList, Integer version){
        newMovieJpaManager.saveAllNewMovieVO(newMovieVOList, version);
    }

    private void saveNewMovieDataList(List<MovieData> movieDataList){
        movieJpaManager.saveAllMovieData(movieDataList);
    }

    private void saveNewMovieDateList(List<ReleaseDate> releaseDateList){
        releaseDateJpaManager.saveReleaseDate(releaseDateList);
    }

    private void saveReMovieList(List<ReleaseDate> releaseDateList){
        List<ReMovieEntity> reMovieList = new ArrayList<>();

        for(ReleaseDate releaseDate : releaseDateList){
            if(releaseDate.getReleaseDateSize() > 1) reMovieList.add(new ReMovieEntity(releaseDate.getMovieCode()));
        }
        reMovieJpaManager.saveAll(reMovieList);
    }

}
