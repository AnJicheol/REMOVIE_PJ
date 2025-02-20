package com.example.removie.kobis.parser;

import com.example.removie.kobis.exception.MovieCodeFailException;
import com.example.removie.log.LogGroup;
import jakarta.validation.constraints.NotBlank;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 영화 식별 코드를 파싱 하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieCodeParser implements MovieIdentity {
    private final static Pattern MOVIE_CODE_PATTERN = Pattern.compile("mstView\\('movie','(\\w+)'\\)");
    private final static String  MOVIE_CODE_CSS_QUERY = "span.ellip.per90 a";

    private final Logger logger = LoggerFactory.getLogger(KOBISMovieCodeParser.class);

    /**
     * @param element 영화 식별 코드 파싱에 필요한 HTML 객체입니다.
     * @return 영화 식별에 사용되는 코드입니다.
     * @throws MovieCodeFailException 영화 코드 파싱 실패 시 발생하는 예외입니다.
     */
    @Override
    public String getMovieIdentity(@NotBlank Element element) {
        Element movieCodeElement = element.selectFirst(MOVIE_CODE_CSS_QUERY);
        validateCssQuery(movieCodeElement);
        return regexMovieCode(movieCodeElement);
    }

    private String regexMovieCode(Element element) {
        Matcher matcher = getMatcher(element);
        validateMatcher(matcher);
        return matcher.group(1).trim();
    }

    private Matcher getMatcher(@NotBlank Element element) {
        return MOVIE_CODE_PATTERN.matcher(element.toString());
    }

    private void validateMatcher(Matcher matcher) {
        if (!matcher.find()) {
            logAndThrow("정규식 파싱 실패 : 정규식 패턴 매칭 실패");
        }
    }

    private void validateCssQuery(Element movieCodeElement) {
        if (movieCodeElement == null) {
            logAndThrow("CSS_Query 파싱 실패 : Element NULL");
        }
    }

    @LogGroup
    private void logAndThrow(String message) {
        logger.error(message);
        throw new MovieCodeFailException(message);
    }
}
