package com.example.removie.kobis.parser;

import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.exception.MovieGroupFailException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 영화관 코드 파싱에 필요한 HTML Elements를 생성하는 클래스입니다.
 *
 * <p>파싱 성능을 높이기 위해 페이지를 최소한에 요소 단위로 분리합니다.</p>
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieDataGroupParser {
    private final static String MOVIE_ELEMENTS_CSS_QUERY = "tr.even";
    private final Logger logger = LoggerFactory.getLogger(KOBISMovieDataGroupParser.class);

    private final DocConnect docConnect;
    private final KOBISPage kobisPage;

    @Autowired
    public KOBISMovieDataGroupParser(DocConnect docConnect, KOBISPage kobisPage) {
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }


    /**
     * HTML 문서에서{@code Document document} 파싱에 필요한 정보를 최소한에 요소 단위로 파싱 합니다.
     *
     * @return 영화별 파싱에 필요한 HTML 정보를 리턴합니다.
     */
    public Elements getMovieDataGroup(){
        Document document = docConnect.responseDoc(kobisPage.mainPage());
        Elements elements = document.select(MOVIE_ELEMENTS_CSS_QUERY);
        validateElements(elements);

        return elements;
    }

    private void validateElements(Elements elements){
        if(elements.isEmpty()){
            logger.error("페이지 변경 가능성 있음 - 영화 정보 그룹 파싱 실패 : Element 비어 있음");
            throw new MovieGroupFailException("영화 정보 그룹 파싱 실패");
        }
    }
}
