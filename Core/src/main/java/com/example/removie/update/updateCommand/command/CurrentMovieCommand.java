package com.example.removie.update.updateCommand.command;

import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.jpaManager.ReleaseJpaManager;
import lombok.Builder;

import java.util.List;

public class CurrentMovieCommand implements ChangeCommand{
    private final ReleaseJpaManager releaseJpaManager;
    private final List<CurrentMovieVO> currentMovieVOList;

    @Builder
    public CurrentMovieCommand(ReleaseJpaManager releaseJpaManager, List<CurrentMovieVO> currentMovieVOList) {
        this.releaseJpaManager = releaseJpaManager;
        this.currentMovieVOList = currentMovieVOList;
    }

    @Override
    public void update(Integer version) {
        releaseJpaManager.saveCurrentMovieList(currentMovieVOList, version);
    }

    public boolean isEmpty(){
        return currentMovieVOList.isEmpty();
    }
}
