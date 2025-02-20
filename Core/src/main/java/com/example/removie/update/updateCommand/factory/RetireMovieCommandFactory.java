package com.example.removie.update.updateCommand.factory;

import com.example.removie.movie.compare.service.RetireMovieCompareService;
import com.example.removie.movie.jpaManager.RetireMovieJpaManager;
import com.example.removie.update.updateCommand.command.ChangeCommand;
import com.example.removie.update.updateCommand.command.RetireMovieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RetireMovieCommandFactory implements CommandFactory{
    private final RetireMovieJpaManager retireMovieJpaManager;
    private final RetireMovieCompareService retireMovieCompareService;

    @Autowired
    public RetireMovieCommandFactory(RetireMovieJpaManager retireMovieJpaManager, RetireMovieCompareService retireMovieCompareService) {
        this.retireMovieJpaManager = retireMovieJpaManager;
        this.retireMovieCompareService = retireMovieCompareService;
    }

    @Override
    public ChangeCommand createCommand() {
        return RetireMovieCommand.builder().
                retireMovieJpaManager(retireMovieJpaManager).
                retireMovieCompareResult(retireMovieCompareService.getRetireMovieCompareResult()).
                build();
    }
}
