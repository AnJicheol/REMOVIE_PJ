package com.example.removie.kobis.cinema;

import com.example.removie.cinema.exception.CinemaParsingFailException;
import com.example.removie.cinema.CinemaData;
import com.example.removie.cinema.CinemaInfoProvider;
import com.example.removie.log.LogGroup;
import jakarta.validation.constraints.NotBlank;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 영화관 정보 파싱하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISCinemaDataParser {
    private final static Pattern CINEMA_CODE_REGEX_PATTERN = Pattern.compile("onclick=\"fn_theaNmClick\\(this, '.*?', '(.*?)', '.*?'\\)");
    private static final Logger logger = LoggerFactory.getLogger(KOBISCinemaDataParser.class);


    /**
     * 상영중인 영화관 코드를 파싱 후 {@link CinemaData} 객체로 생성합니다.
     *
     * @param element 특정 영화의 상영관 페이지를 포함한 객체입니다.
     *
     * @return 추출된 영화관 정보를 포함한 {@link CinemaData} 객체를 반환합니다.
     * @throws CinemaParsingFailException 정규식 매칭 실패 시 발생하는 예외입니다.
     */
    public CinemaData extractCinemaData(@NotBlank Element element) {
        Matcher matcher = getMatcher(element);
        verifiedMatcher(matcher, element);

        CinemaData cinemaData = CinemaInfoProvider.getMovieInfo(matcher.group(1));
        verifiedCinemaData(cinemaData, element);
        return cinemaData;
    }

    private Matcher getMatcher(@NotBlank Element element){
        return CINEMA_CODE_REGEX_PATTERN.matcher(element.toString());
    }

    private void verifiedMatcher(Matcher matcher, Element element){
        if (!matcher.find()) {
            log("패턴 매칭 실패", element);
            throw new CinemaParsingFailException("상영관 코드 정규식 파싱 실패 : 패턴 매칭 실패");
        }
    }

    /**
     * 영화관 코드에 유효성을 검증합니다.
     *
     * <p>
     * {@link CinemaInfoProvider} 에 등록되지 않은 값을 검증하며,
     * 잘못 파싱 된 값을 방지하기 위한 조치입니다.
     * </p>
     *
     * <p>{@code cinemaData.isINVALID()} 는 빈객체 입니다.</p>
     *
     * @param cinemaData 추출된 상영관 데이터입니다.
     * @param element 원본 HTML Element입니다.
     */
    private void verifiedCinemaData(CinemaData cinemaData, Element element){
        if (cinemaData.isINVALID()) {
            log("영화관 코드가 유효하지 않음", element);
        }
    }

    @LogGroup
    private void log(String message , Element element){
        logger.error("상영관 파싱 실패 : {} ", message);
        logger.debug("Element : {}", element);
    }
}
