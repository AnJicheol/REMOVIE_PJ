package com.example.removie.kobis.parser;


import com.example.removie.document.DocConnect;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.log.LogGroup;
import com.example.removie.movie.jpaManager.ReMovieJpaManager;
import com.example.removie.movie.vo.ReleaseDate;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/**
 * 상영 날짜를 파싱 하는 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Component
public class KOBISMovieDateParser {
    private final static String MOVIE_DATE_CSS_QUERY = "dt:contains(개봉일) + dd";

    private final Logger logger = LoggerFactory.getLogger(KOBISMovieDateParser.class);

    private final KOBISReMovieDateParser reMovieDateParser;
    private final ReMovieJpaManager reMovieJpaManager;
    private final DocConnect docConnect;
    private final KOBISPage kobisPage;


    @Autowired
    public KOBISMovieDateParser(ReMovieJpaManager reMovieJpaManager, DocConnect docConnect, KOBISPage kobisPage) {
        this.reMovieDateParser = new KOBISReMovieDateParser(docConnect, kobisPage);
        this.reMovieJpaManager = reMovieJpaManager;
        this.docConnect = docConnect;
        this.kobisPage = kobisPage;
    }

    /**
     * 상영 날짜를 파싱 후 리턴합니다.
     *
     * <p>
     * 상영 날짜를 파싱 후 {@link ReleaseDate}로 리턴합니다. 이때 상영 날짜는 비어있는 값일 수 있습니다.
     * 새로 개봉한 영화에 경우 단일에 날짜만 파싱 하지만 재개봉 영화일 경우 개봉 이력 또한 파싱 합니다.
     * </p>
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 상영 날짜 정보를 리턴합니다.
     */
    public ReleaseDate getParsingResult(@NotBlank String movieCode){
        Document document = docConnect.responseDoc(kobisPage.detailPage(movieCode));
        LocalDate date = createLocalDate(document);

        List<LocalDate> localDateList = Stream.concat(
                (date == null || reReleaseCheck(date, movieCode)) ? getReLocalDateList(movieCode).stream() : Stream.empty(),
                (date != null) ? Stream.of(date) : Stream.empty()
        ).toList();

        return new ReleaseDate(movieCode, localDateList);
    }

    /**
     * 상영 날짜를 파싱 후 리턴합니다.
     *
     * @param document 개볼 정보를 갖고 있는 HTML 문서입니다.
     * @return 상영 날짜 정보를 리턴합니다.
     */
    @LogGroup
    private LocalDate createLocalDate(@NotBlank Document document){
        String date = parseDateToDoc(document);

        if(date == null) return null;
        try {
            return LocalDate.parse(date);
        }

        catch (DateTimeException e ){
            log("페이지 변경 가능성 있음 - 상영 날짜 파싱 실패 : 날짜 형식이 맞지 않음", date);
            return null;
        }
    }

    /**
     * 날짜를 파싱 합니다.
     *
     * @param document 날짜를 파싱 하기 위한 HTML 문서입니다.
     * @return 상영 날짜 리턴합니다.
     */
    private String parseDateToDoc(Document document){
        Element element = document.selectFirst(MOVIE_DATE_CSS_QUERY);

        if(element != null) return element.text();
        return null;
    }

    /**
     * 재개봉 영화에 한에서 작동하는 메서드입니다 상영 이력을 파싱 합니다.
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 재개봉 영화에 상영 이력을 리턴합니다.
     */
    private List<LocalDate> getReLocalDateList(@NotBlank String movieCode){
        List<LocalDate> reDateList = reMovieDateParser.getParsingResult(movieCode);

        if(reDateList.isEmpty()){
            reDateList.add(LocalDate.now());
        }

        return reDateList;
    }

    /**
     * 재개봉 여부를 판별합니다, 중복을 방지하기 위해 검증합니다. {@code !reMovieJpaManager.existsByMovieCode(movieCode)}
     *
     * @param localDate 파싱 된 첫 상영 날자입니다.
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 상영 날짜 정보를 리턴합니다.
     */
    private boolean reReleaseCheck(LocalDate localDate, @NotBlank String movieCode){
        return localDate.isBefore(LocalDate.now().minusMonths(3)) &&
                !reMovieJpaManager.existsByMovieCode(movieCode);
    }

    @LogGroup
    private void log(String message, Object o){
        logger.warn(message);
        logger.debug("상세 내용 : {}", o);
    }

    static class KOBISReMovieDateParser{

        private final static String REMOVIE_DATE_TABLE_CSS_QUERY = "table.tbl_comm.topico[style*=\"display:none\"]";
        private final static String REMOVIE_DATE_EL_CSS_QUERY = "tbody tr";
        private static final Pattern VALID_DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

        private final Logger logger = LoggerFactory.getLogger(KOBISReMovieDateParser.class);
        private final DocConnect docConnect;
        private final KOBISPage kobisPage;

        KOBISReMovieDateParser(DocConnect docConnect, KOBISPage kobisPage) {
            this.docConnect = docConnect;
            this.kobisPage = kobisPage;
        }


        /**
         * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
         * @return 재개봉 영화에 상영 이력을 리턴합니다.
         */
        public List<LocalDate> getParsingResult(@NotBlank String movieCode){
            return getDateAndPress(parseDate(movieCode));
        }


        /**
         * 재개봉 영화에 상영 이력을 문자열 형태로 리스트에 담아 리턴합니다.
         *
         * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
         * @return 재개봉 영화에 상영 이력을 리턴합니다.
         */
        private List<String> parseDate(@NotBlank String movieCode){

            List<String> dateList = new ArrayList<>();
            Document document = docConnect.responseDoc(kobisPage.removieDatePage(movieCode));
            Element element = document.selectFirst(REMOVIE_DATE_TABLE_CSS_QUERY);

            if(element != null){
                dateList = createDateList(element);
            }
            else{
                log("패턴 매칭 실패 : Element Null", "");
            }
            return dateList;
        }


        /**
         * 같은 월에 개봉한 영화를 압축하는 메서드입니다.
         *
         * @param removieDate 파싱 된 날짜 리스트입니다.
         * @return 상영 이력을 리턴합니다.
         */
        private List<LocalDate> getDateAndPress(List<String> removieDate){
            List<LocalDate> dateList = new ArrayList<>();
            String lastMonth = "";

            for(String date : removieDate){
                String currentMonth = date.substring(0, 7);

                if (lastMonth.equals(currentMonth)) continue;
                addLocalDate(dateList, date);
                lastMonth = currentMonth;
            }

            return dateList;
        }

        /**
         * 파싱 된 문자열 형태에 날짜를 {@link LocalDate}로 변환 후 리스트에 추가합니다.
         *
         * @param dateList 파싱 된 날짜를 보관하는 리스트입니다.
         * @param date 파싱 된 날짜입니다.
         */
        private void addLocalDate(@NonNull List<LocalDate> dateList, @NotBlank String date){
            try {
                dateList.add(LocalDate.parse(date));
            }catch (DateTimeException e){
                log("addLocalDate- 문자열 이 날짜 형식과 맞지 않음", date);
            }
        }

        /**
         * 재개봉 영화에 상영 이력을 문자열 형태로 리스트에 담아 리턴합니다.
         * 문자열로 리턴하는 이유는 같은 월에 개봉한 이력을 합치고 {@link LocalDate}
         * 로 변환할 때 오버헤드를 줄이기 위함입니다.
         *
         * @param element 파싱에 필요한 HTML 입니다.
         * @return 재개봉 영화에 상영 이력을 리턴합니다.
         */
        private List<String> createDateList(@NotBlank Element element) {
            return element.select(REMOVIE_DATE_EL_CSS_QUERY).stream()
                    .map(e -> e.selectFirst("td"))
                    .filter(Objects::nonNull)
                    .map(Element::text)
                    .filter(this::isValidDateRegex)
                    .toList();
        }

        private boolean isValidDateRegex(@NotBlank String date) {
            boolean isValid = VALID_DATE_PATTERN.matcher(date).matches();

            if(!isValid) {
                log("isValidDateRegex - 문자열 이 날짜 형식과 맞지 않음", date);
            }
            return isValid;
        }

        @LogGroup
        private void log(String message, Object o){
            logger.warn(message);
            logger.debug("상세 내용 : {}", o);
        }
    }
}


