package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.compare.result.RetireMovieCompareResult;
import com.example.removie.movie.vo.RetireMovieVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * 개봉 종료한 영화를 {@link RetireMovieCompareResult}로 리턴합니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Component
public class RetireMovieCompareImpl implements RetireMovieCompare{

    /**
     *
     * @param newReleaseMap 최신 영화 정보를 갖고 있는 객체입니다.      랭킹, 영화 정보 등이 포함됩니다.
     * @param currentMovieVOList 기존 영화 정보를 갖고 있는 객체입니다. 랭킹, 영화 정보 등이 포함됩니다.
     * @return 개봉 종료한 영화를 리턴합니다.
     */
    public RetireMovieCompareResult compare(NewReleaseMap newReleaseMap, List<CurrentMovieVO> currentMovieVOList){

        Set<String> newMovieCodes = newReleaseMap.getMovieCodesSet();

        List<RetireMovieVO> retireMovieVOList = currentMovieVOList.stream()
                .filter(currentMovieVO -> !newMovieCodes.contains(currentMovieVO.getMovieCode()))
                .map(currentMovieVO -> new RetireMovieVO(currentMovieVO.getMovieCode()))
                .collect(Collectors.toList());

        return new RetireMovieCompareResult(retireMovieVOList);
    }
}
