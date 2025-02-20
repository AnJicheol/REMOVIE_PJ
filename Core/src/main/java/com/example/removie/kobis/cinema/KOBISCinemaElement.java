package com.example.removie.kobis.cinema;

import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.log.LogGroup;
import jakarta.validation.constraints.NotBlank;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * 영화관 코드 파싱을 위한 HTML Elements를 생성하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISCinemaElement {
    private final static String CINEMA_CUT_DIV_CLASS_CSS_QUERY = "div.info.info2";
    private final static String CINEMA_CUT_TABLE_CSS_QUERY = "a[onclick]";

    private static final Logger logger = LoggerFactory.getLogger(KOBISCinemaElement.class);
    private final DocConnect docConnect;
    private final KOBISPage kobisPage;

    @Autowired
    public KOBISCinemaElement(DocConnect docConnect, KOBISPage kobisPage) {
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }

    /**
     *  HTML 문서에서 영화관 정보를 각각 블록 단위로 파싱 합니다.
     *
     * @param movieCode 파싱에 대상이 되는 영화 식별 코드입니다.
     * @return 영화관별 정보가 포함된 개별 블록 목록입니다.
     */
    @LogGroup
    public Elements getCinemaCodeElements(@NotBlank String movieCode){
        Document document = docConnect.responseDoc(kobisPage.cinemaPage(movieCode));
        Elements cinemaDivElements = document.select(CINEMA_CUT_DIV_CLASS_CSS_QUERY);
        Elements cinemaCodeElements = cinemaDivElements.select(CINEMA_CUT_TABLE_CSS_QUERY);

        if(cinemaCodeElements.isEmpty()){
            logger.error("영화관 코드 파싱 실패 : Elements 비어 있음");
            logger.debug(
                    """     
                            MovieCode : {}
                            1차 파싱 cinemaDivElements : {}
                            """,
                    movieCode, cinemaDivElements
            );
        }

        return cinemaCodeElements;
    }
}
