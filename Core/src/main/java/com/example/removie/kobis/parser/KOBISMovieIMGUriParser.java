package com.example.removie.kobis.parser;


import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBIS;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.log.LogGroup;
import jakarta.validation.constraints.NotBlank;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * 영화 포스터 URI를 파싱 하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieIMGUriParser {
    private final static String KOBIS_IMG_ATTR = "href";
    private final static String KOBIS_IMG_CSS_QUERY = "a.fl.thumb";
    private final Logger logger = LoggerFactory.getLogger(KOBISMovieIMGUriParser.class);

    private final DocConnect docConnect;
    private final KOBISPage kobisPage;

    @Autowired
    public KOBISMovieIMGUriParser(DocConnect docConnect, KOBISPage kobisPage) {
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }


    /**
     * 영화 포스터 URI를 파싱 합니다.
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 영화 포스터 URI를 문자열 형태로 리턴합니다. null 일 수 있습니다.
     */
    @LogGroup
    public @Nullable String getParsingResult(@NotBlank String movieCode){
        Elements elements = docConnect
                .responseDoc(kobisPage.detailPage(movieCode))
                .select(KOBIS_IMG_CSS_QUERY);

        if(elements.hasAttr("href")){
            return KOBIS.KOBIS_BASE_URI_FOR_IMG.getValue() + elements.attr(KOBIS_IMG_ATTR);
        }

        logger.warn("이미지 URI 파싱 실패 : Elements 에 href 속성 존재 하지 않음");
        logger.debug("Elements : {}", elements);
        return null;
    }

}
