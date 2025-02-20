package com.example.removie.update.updateCommand.factory;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.jpaManager.ReleaseJpaManager;
import com.example.removie.movie.vo.BasicMovieVO;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.update.CompareTargetProvider;
import com.example.removie.update.updateCommand.command.ChangeCommand;
import com.example.removie.update.updateCommand.command.CurrentMovieCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**
 *현재 상영 중인 영화 상태를 최신화하는 커맨드를 생성합니다.
 *해당 데이터는 클라이언트에 페이징, 동기화, 비교 프로세스 등에 사용됩니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class CurrentMovieCommandFactory implements CommandFactory{
    private final CompareTargetProvider compareTargetProvider;
    private final ReleaseJpaManager releaseJpaManager;

    @Autowired
    public CurrentMovieCommandFactory(CompareTargetProvider compareTargetProvider, ReleaseJpaManager releaseJpaManager) {
        this.compareTargetProvider = compareTargetProvider;
        this.releaseJpaManager = releaseJpaManager;
    }

    /**
     * @return 현재 상영 중인 영화 상태를 최신화하는 커맨드를 생성합니다.
     */
    @Override
    public ChangeCommand createCommand() {
        return CurrentMovieCommand.builder().
                releaseJpaManager(releaseJpaManager).
                currentMovieVOList(getNewCurrentMovieList(compareTargetProvider.getParsingResult())).
                build();
    }

    /**
     * @param newReleaseMap 파싱 된 개봉 중인 영화 상태를 포함하는 객체입니다.
     * @return 현재 상영 중인 영화 목록을 {@link CurrentMovieVO}로 리턴합니다.
     */
    private List<CurrentMovieVO> getNewCurrentMovieList(NewReleaseMap newReleaseMap){
        return newReleaseMap.getMovieCodesSet().stream()
                .map(movieCode -> {
                    BasicMovieVO basicMovieVO = newReleaseMap.getBasicMovieVO(movieCode);
                    return new CurrentMovieVO(basicMovieVO.getMovieCode(), basicMovieVO.getRanking());
                })
                .collect(Collectors.toList());
    }
}
