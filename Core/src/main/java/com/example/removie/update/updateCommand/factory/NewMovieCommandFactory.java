package com.example.removie.update.updateCommand.factory;

import com.example.removie.movie.compare.service.NewMovieCompareService;
import com.example.removie.movie.jpaManager.MovieJpaManager;
import com.example.removie.movie.jpaManager.NewMovieJpaManager;
import com.example.removie.movie.jpaManager.ReMovieJpaManager;
import com.example.removie.movie.jpaManager.ReleaseDateJpaManager;
import com.example.removie.update.CompareTargetProvider;
import com.example.removie.update.updateCommand.command.ChangeCommand;
import com.example.removie.update.updateCommand.command.NewMovieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NewMovieCommandFactory implements CommandFactory{
    private final NewMovieJpaManager newMovieJpaManager;
    private final MovieJpaManager movieJpaManager;
    private final ReleaseDateJpaManager releaseDateJpaManager;
    private final ReMovieJpaManager reMovieJpaManager;
    private final NewMovieCompareService newMovieCompareService;

    @Autowired
    public NewMovieCommandFactory(NewMovieJpaManager newMovieJpaManager, MovieJpaManager movieJpaManager, ReleaseDateJpaManager releaseDateJpaManager, ReMovieJpaManager reMovieJpaManager, NewMovieCompareService newMovieCompareService, CompareTargetProvider compareTargetProvider) {
        this.newMovieJpaManager = newMovieJpaManager;
        this.movieJpaManager = movieJpaManager;
        this.releaseDateJpaManager = releaseDateJpaManager;
        this.reMovieJpaManager = reMovieJpaManager;
        this.newMovieCompareService = newMovieCompareService;
    }

    @Override
    public ChangeCommand createCommand() {
        return NewMovieCommand.builder().
                newMovieCompareResult(newMovieCompareService.getNewMovieCompareResult()).
                movieJpaManager(movieJpaManager).
                newMovieJpaManager(newMovieJpaManager).
                releaseDateJpaManager(releaseDateJpaManager).
                reMovieJpaManager(reMovieJpaManager).
                build();
    }
}
