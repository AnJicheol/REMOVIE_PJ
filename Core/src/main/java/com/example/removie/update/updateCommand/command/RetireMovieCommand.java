package com.example.removie.update.updateCommand.command;

import com.example.removie.movie.compare.result.RetireMovieCompareResult;
import com.example.removie.movie.jpaManager.RetireMovieJpaManager;
import lombok.Builder;

public class RetireMovieCommand implements ChangeCommand{
    private final RetireMovieJpaManager retireMovieJpaManager;
    private final RetireMovieCompareResult retireMovieCompareResult;


    @Builder
    public RetireMovieCommand(RetireMovieJpaManager retireMovieJpaManager, RetireMovieCompareResult retireMovieCompareResult) {
        this.retireMovieCompareResult = retireMovieCompareResult;
        this.retireMovieJpaManager = retireMovieJpaManager;
    }


    @Override
    public void update(Integer version) {
        retireMovieJpaManager.saveRetireMovie(retireMovieCompareResult.getRetireMovieVOList(), version);
    }

    @Override
    public boolean isEmpty() {
        return retireMovieCompareResult.isEmpty();
    }
}


