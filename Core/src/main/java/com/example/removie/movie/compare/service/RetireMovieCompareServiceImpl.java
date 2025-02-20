package com.example.removie.movie.compare.service;

import com.example.removie.movie.compare.RetireMovieCompare;
import com.example.removie.movie.compare.result.RetireMovieCompareResult;
import com.example.removie.movie.jpaManager.ReleaseJpaManager;
import com.example.removie.update.CompareTargetProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * {@link RetireMovieCompare}에 필요한 값을 전달하고 결과를 반환하는 서비스 클래스입니다.
 *
 * <p>
 * 이 클래스는 비교 로직이 변경될 가능성을 고려하여 설계되었습니다.
 * {@link RetireMovieCompare}의 변경이 직접적으로 전파되지 않도록 하며, 항상 일관된 동작을 보장합니다.
 * </p>
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class RetireMovieCompareServiceImpl implements RetireMovieCompareService{
    private final ReleaseJpaManager releaseJpaManager;
    private final RetireMovieCompare retireMovieCompare;
    private final CompareTargetProvider compareTargetProvider;

    @Autowired
    public RetireMovieCompareServiceImpl(ReleaseJpaManager releaseJpaManager, RetireMovieCompare retireMovieCompare, CompareTargetProvider compareTargetProvider) {
        this.releaseJpaManager = releaseJpaManager;
        this.retireMovieCompare = retireMovieCompare;
        this.compareTargetProvider = compareTargetProvider;
    }

    @Override
    public RetireMovieCompareResult getRetireMovieCompareResult(){
        return retireMovieCompare.compare(
                compareTargetProvider.getParsingResult(),
                releaseJpaManager.getCurrentMovieList()
        );
    }
}


