package com.example.removie.kobis.parser;

import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.exception.KOBISTitleParsingFailException;
import com.example.removie.log.LogGroup;
import jakarta.validation.constraints.NotBlank;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 영화 제목을 파싱 하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieTitleParser {
    private final static String MOVIE_TITLE_CSS_QUERY = "div.hd_layer strong";
    private final static String NULL_MESSAGE = "영화 타이틀 파싱 실패 : Element 가 null";
    private final static String EMPTY_MESSAGE = "영화 타이틀 파싱 실패 : Element 가 비어 있음";

    private final Logger logger = LoggerFactory.getLogger(KOBISMovieTitleParser.class);


    private final DocConnect docConnect;
    private final KOBISPage kobisPage;

    @Autowired
    public KOBISMovieTitleParser(DocConnect docConnect, KOBISPage kobisPage) {
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }

    /**
     * 영화 제목을 파싱 합니다.
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 영화에 제목을 리턴합니다.
     */
    public String getParsingResult(@NotBlank String movieCode){
        Document document = docConnect
                .responseDoc(kobisPage.detailPage(movieCode));

        Element titleElement = document.selectFirst(MOVIE_TITLE_CSS_QUERY);
        return isValidTitle(titleElement);

    }

    /**
     * 데이터 검증 후 영화 제목을 추출합니다.
     *
     * @param titleElement 파싱 대상이 되는 HTML 요소입니다.
     * @return 영화에 제목을 리턴합니다.
     */
    private String isValidTitle(Element titleElement){
        if(titleElement == null || titleElement.text().isEmpty()){

            String message = (titleElement == null) ? NULL_MESSAGE : EMPTY_MESSAGE;
            log(message);
            throw new KOBISTitleParsingFailException(message);
        }
        return titleElement.text();
    }

    private void log(String message){
        logger.error(message);
    }

}
