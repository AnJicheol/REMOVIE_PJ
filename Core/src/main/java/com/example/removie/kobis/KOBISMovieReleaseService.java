package com.example.removie.kobis;


import com.example.removie.kobis.parser.KOBISMovieCodeParser;
import com.example.removie.kobis.parser.KOBISMovieDataGroupParser;
import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.vo.BasicMovieVO;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * 최신 영화 정보를 생성하는 서비스입니다. 최신 상영정보와 영화 코드 등이 포함됩니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class KOBISMovieReleaseService implements NewReleasesService {
    private final KOBISMovieDataGroupParser kobisMovieDataGroupParser;
    private final KOBISMovieCodeParser kobisMovieCodeParser;

    @Autowired
    public KOBISMovieReleaseService(KOBISMovieDataGroupParser kobisMovieDataGroupParser, KOBISMovieCodeParser kobisMovieCodeParser){
        this.kobisMovieDataGroupParser = kobisMovieDataGroupParser;
        this.kobisMovieCodeParser = kobisMovieCodeParser;
    }


/**
 *  테스트 시 불필요한 트래픽을 줄이기 위해 아래 메서드를 사용합니다.
 *
    public NewReleaseMap getReleaseMap(){
        Map<String, BasicMovieVO> resultMap = new HashMap<>();

        int ranking = 1;
        for (Element element : kobisMovieDataGroupParser.getMovieDataGroup()){
            BasicMovieVO movieVO = BasicMovieVO.builder().
                    ranking(ranking++).
                    movieCode(kobisMovieCodeParser.getParsingResult(element)).
                    build();
            resultMap.put(movieVO.getMovieCode(), movieVO);
        }
        return new NewReleaseMap(resultMap);
    }
*/


    /**
     * 신규 개봉 영화 목록을 파싱 하여 반환합니다.
     *{@link KOBISMovieDataGroupParser#getMovieDataGroup()}는
     * 개별 영화를 파싱 하기 위한 HTML 요소를 가져옵니다.
     *
     * <p>
     * ranking은 파싱 사이트에 신뢰할 수 있는 데이터가 없어
     * 예매율 기준으로 정렬된 데이터에 의존하여 직접 생성합니다
     * </p>
     *
     * @return 신규 개봉 영화 정보가 포함된 {@link NewReleaseMap}
     */
    public NewReleaseMap getReleaseMap(){
        Map<String, BasicMovieVO> resultMap = new HashMap<>();

        int ranking = 1;
        for (Element element : kobisMovieDataGroupParser.getMovieDataGroup()){
            if(ranking == 20) break;

            BasicMovieVO movieVO = BasicMovieVO.builder().
                    ranking(ranking++).
                    movieCode(kobisMovieCodeParser.getMovieIdentity(element)).
                    build();
            resultMap.put(movieVO.getMovieCode(), movieVO);
        }
        return new NewReleaseMap(resultMap);
    }

}
