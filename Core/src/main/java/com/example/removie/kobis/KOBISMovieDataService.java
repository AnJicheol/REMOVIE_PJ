package com.example.removie.kobis;

import com.example.removie.kobis.parser.KOBISMovieInfoParser;
import com.example.removie.kobis.parser.KOBISMovieTitleParser;
import com.example.removie.aws.s3.AWSMovieIMGURLService;
import com.example.removie.movie.vo.MovieData;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 영화 데이터를 생성하는 파사드 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
@Service
public class KOBISMovieDataService implements MovieDataService {

    private final AWSMovieIMGURLService AWSMovieIMGURLService;
    private final KOBISMovieInfoParser kobisMovieInfoParser;
    private final KOBISMovieTitleParser kobisMovieTitleParser;

    @Autowired
    public KOBISMovieDataService(KOBISMovieInfoParser kobisMovieInfoParser, AWSMovieIMGURLService AWSMovieIMGURLService, KOBISMovieTitleParser kobisMovieTitleParser) {
        this.kobisMovieInfoParser = kobisMovieInfoParser;
        this.AWSMovieIMGURLService = AWSMovieIMGURLService;
        this.kobisMovieTitleParser = kobisMovieTitleParser;
    }

    /**
     * 매개변수로 받은 영화에 정보를 생성합니다, 과정에서 파싱과 AWS S3 업로드가 동반되며
     * PosterImg 와 info는 {@code Null}일 수 있습니다.
     *
     * @param movieCode 파싱 대상이 되는 영화에 식별 코드입니다.
     * @return 영화에 데이터를 리턴합니다. {@link MovieData}
     */
    @Override
    public MovieData getMovieData(@NotBlank String movieCode){
        return MovieData.builder().
                movieCode(movieCode).
                info(kobisMovieInfoParser.getParsingResult(movieCode)).
                posterIMG(AWSMovieIMGURLService.uploadMovieImageAndReturnURL(movieCode)).
                title(kobisMovieTitleParser.getParsingResult(movieCode)).
                build();
    }

}
