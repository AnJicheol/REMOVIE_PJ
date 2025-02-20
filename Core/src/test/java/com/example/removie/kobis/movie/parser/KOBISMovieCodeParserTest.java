package com.example.removie.kobis.movie.parser;

import com.example.removie.kobis.exception.MovieCodeFailException;
import com.example.removie.kobis.parser.KOBISMovieCodeParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class KOBISMovieCodeParserTest {

    @Test
    public void getMovieCodeTest(){
        //given
        KOBISMovieCodeParser kobisMovieCodeParser = new KOBISMovieCodeParser();

        Element element1 = Jsoup.parse(
        """
                KOBIS 정보가 포함되어 제거하였습니다.
            """
        );
        //when
        String movieCode1 = kobisMovieCodeParser.getMovieIdentity(element1);

        //then
        assertEquals("20240737", movieCode1);
    }

    @Test
    public void validateTest(){
        //given
        KOBISMovieCodeParser kobisMovieCodeParser = new KOBISMovieCodeParser();

        Element element2 = Jsoup.parse("TEST");
        Element element3 = Jsoup.parse("<td class=\"tal\"><span class=\"ellip per90\"> <a href=\"http://www.naver.com\">Go Naver</a> </span></td>");


        //when

        MovieCodeFailException exception2 = assertThrows(MovieCodeFailException.class, () -> {
            kobisMovieCodeParser.getMovieIdentity(element2);
        });
        MovieCodeFailException exception3 = assertThrows(MovieCodeFailException.class, () -> {
            kobisMovieCodeParser.getMovieIdentity(element3);
        });


        //then

        assertEquals("CSS_Query 파싱 실패 : Element NULL", exception2.getMessage());
        assertEquals("정규식 파싱 실패 : 정규식 패턴 매칭 실패", exception3.getMessage());
    }

}
