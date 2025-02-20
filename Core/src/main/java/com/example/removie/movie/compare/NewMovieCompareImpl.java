package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.kobis.KOBISMovieDataService;
import com.example.removie.kobis.MovieDataService;
import com.example.removie.kobis.parser.KOBISMovieDateParser;
import com.example.removie.movie.vo.*;
import com.example.removie.movie.compare.result.NewMovieCompareResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 최신 상영 데이터에서 새로 개봉한 영화를 추출 하고 각 영화에 대한 영화 정보, 상영 날짜 등을
 * {@link KOBISMovieDateParser}, {@link MovieDataService} 을 통해 파싱 합니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */

@Component
public class NewMovieCompareImpl implements NewMovieCompare {
    private final MovieDataService kobisMovieDataService;
    private final KOBISMovieDateParser kobisMovieDateParser;


    @Autowired
    public NewMovieCompareImpl(MovieDataService kobisMovieDataService, KOBISMovieDateParser kobisMovieDateParser) {
        this.kobisMovieDataService = kobisMovieDataService;
        this.kobisMovieDateParser = kobisMovieDateParser;
    }

    /**
     * 새로 개봉한 영화를 추출 하여 각 영화에 대한 영화 정보, 상영 날짜 등을
     *  * {@link KOBISMovieDateParser}, {@link MovieDataService} 을 통해 파싱 후
     *  {@link NewMovieCompareResult} 객체로 리턴합니다.
     *
     * @param newReleaseMap 최신 영화 정보를 갖고 있는 객체입니다.      랭킹, 영화 정보 등이 포함됩니다.
     * @param currentMovieVOList 기존 영화 정보를 갖고 있는 객체입니다. 랭킹, 영화 정보 등이 포함됩니다.
     * @return 신규 개봉한 영화 리스트와 각 영화에 영화 정보, 상영 날짜 등을 포함한 객체를 리턴합니다.
     */
    public NewMovieCompareResult compare(NewReleaseMap newReleaseMap, List<CurrentMovieVO> currentMovieVOList){
        List<NewMovieVO> newMovieVOList = getNewMovieList(newReleaseMap, currentMovieVOList);
        List<ReleaseDate> releaseDateList = getReleaseDate(newMovieVOList);
        List<MovieData> movieDataList = getNewMovieData(newMovieVOList);

        return NewMovieCompareResult.builder().
                newMovieList(newMovieVOList).
                releaseDateList(releaseDateList).
                newMovieDataList(movieDataList).
                build();
    }


    /**
     * @param newReleaseMap 최신 영화 정보를 갖고 있는 객체입니다.      랭킹, 영화 정보 등이 포함됩니다.
     * @param currentMovieVOList 기존 영화 정보를 갖고 있는 객체입니다. 랭킹, 영화 정보 등이 포함됩니다.
     * @return 신규 개봉한 영화에 리스트를 리턴합니다.
     */
    private List<NewMovieVO> getNewMovieList(NewReleaseMap newReleaseMap, List<CurrentMovieVO> currentMovieVOList){

        Set<String> currentMovieCodes = currentMovieVOList.stream()
                .map(CurrentMovieVO::getMovieCode)
                .collect(Collectors.toSet());

        return  newReleaseMap.getMovieCodesSet().stream()
                .filter(movieCode -> !currentMovieCodes.contains(movieCode))
                .map(movieCode -> {

                    BasicMovieVO basicMovieVO = newReleaseMap.getBasicMovieVO(movieCode);
                    return new NewMovieVO(movieCode, basicMovieVO.getRanking());
                })
                .collect(Collectors.toList());
    }

    /**
     * 신규 개봉 영화에 상영 날짜를 리턴합니다.
     * {@link KOBISMovieDateParser}를 통해 값을 얻기 때문에 외부 통신을 동반합니다.
     *
     * @param newMovieVOList 신규 개봉한 영화 리스트입니다.
     * @return 상영 날짜를 리턴합니다.
     */
    private List<ReleaseDate> getReleaseDate(List<NewMovieVO> newMovieVOList) {
        return newMovieVOList.stream()
                .map(newMovieVO -> kobisMovieDateParser.getParsingResult(newMovieVO.getMovieCode()))
                .collect(Collectors.toList());
    }

    /**
     * 신규 개봉 영화에 정보를 리턴합니다.
     * {@link KOBISMovieDataService}를 통해 값을 얻기 때문에 외부 통신을 동반합니다.
     *
     * @param newMovieVOList 신규 개봉한 영화 리스트입니다.
     * @return 영화 정보를 리턴합니다.
     */
    private List<MovieData> getNewMovieData(List<NewMovieVO> newMovieVOList){
        return newMovieVOList.stream()
                .map(newMovieVO -> kobisMovieDataService.getMovieData(newMovieVO.getMovieCode()))
                .collect(Collectors.toList());
    }
}
