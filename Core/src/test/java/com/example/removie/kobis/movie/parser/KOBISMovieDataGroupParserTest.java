package com.example.removie.kobis.movie.parser;


import com.example.removie.document.DocConnect;
import com.example.removie.document.DocConnection;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.exception.MovieGroupFailException;
import com.example.removie.kobis.parser.KOBISMovieDataGroupParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KOBISMovieDataGroupParserTest {


    @InjectMocks private KOBISMovieDataGroupParser kobisMovieDataGroupParser;
    @Mock private DocConnect docConnect;
    @Mock private KOBISPage kobisPage;


    @Test
    public void getMovieDataGroupTest(){
        //given

        Document document = Jsoup.parse("""
                KOBIS 정보가 포함되어 제거하였습니다.
                """);


        DocConnection docConnection1 = mock(DocConnection.class);
        when(kobisPage.mainPage()).thenReturn(docConnection1);

        when(docConnect.responseDoc(kobisPage.mainPage())).thenReturn(document);

        //when
        Elements elements = kobisMovieDataGroupParser.getMovieDataGroup();

        //then
        assertEquals(2, elements.size());
    }

    @Test
    public void getMovieDataGroupFailTest(){
        //given
        Document document = Jsoup.parse("TEST - TEST");

        DocConnection docConnection1 = mock(DocConnection.class);
        when(kobisPage.mainPage()).thenReturn(docConnection1);

        when(docConnect.responseDoc(kobisPage.mainPage())).thenReturn(document);

        //when
        MovieGroupFailException exception = assertThrows(MovieGroupFailException.class, kobisMovieDataGroupParser::getMovieDataGroup);

        //then
        assertEquals("영화 정보 그룹 파싱 실패", exception.getMessage());
    }

}
