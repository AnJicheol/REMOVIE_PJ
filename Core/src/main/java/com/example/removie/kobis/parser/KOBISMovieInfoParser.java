package com.example.removie.kobis.parser;


import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBISPage;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 영화 장르 상영 시간 등에 정보를 파싱 합니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieInfoParser {
    private final static String KOBIS_INFO_CSS_QUERY = "dt:contains(요약정보) + dd";
    private final Logger logger = LoggerFactory.getLogger(KOBISMovieInfoParser.class);


    private final DocConnect docConnect;
    private final KOBISPage kobisPage;

    @Autowired
    public KOBISMovieInfoParser(DocConnect docConnect, KOBISPage kobisPage) {
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }

    /**
     * 영화 정보를 파싱 합니다.
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 영화에 장르 상영 날짜 등 정보를 리턴합니다.
     */
    public String getParsingResult(String movieCode){
        Document document = docConnect
                .responseDoc(kobisPage.detailPage(movieCode));

        Element element = document.selectFirst(KOBIS_INFO_CSS_QUERY);

        if(element == null){
            logger.warn("페이지 변경 가능성 있음 - KOBISMovieInfoParser : Element 비어 있음");
            return "";
        }
        return element.text();
    }

}
