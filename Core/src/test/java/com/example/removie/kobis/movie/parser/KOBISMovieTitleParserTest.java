package com.example.removie.kobis.movie.parser;

import com.example.removie.document.DocConnect;
import com.example.removie.document.DocConnection;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.exception.KOBISTitleParsingFailException;
import com.example.removie.kobis.parser.KOBISMovieTitleParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KOBISMovieTitleParserTest {


    @InjectMocks KOBISMovieTitleParser kobisMovieTitleParser;
    @Mock DocConnect docConnect;
    @Mock KOBISPage kobisPage;

    @Test
    public void getMovieTitleTest(){
        //given

        Document document1 = Jsoup.parse("""
                           KOBIS 정보가 포함되어 제거하였습니다.
                """
        );

        Document document2 = Jsoup.parse("""
                          KOBIS 정보가 포함되어 제거하였습니다.
                """
        );
        Document document3 = Jsoup.parse("TEST - TEST");
        String movieCode1 = "TEST";
        String movieCode2 = "Empty";
        String movieCode3 = "NULL";

        DocConnection docConnection1 = mock(DocConnection.class);
        DocConnection docConnection2 = mock(DocConnection.class);
        DocConnection docConnection3 = mock(DocConnection.class);

        when(kobisPage.detailPage(movieCode1)).thenReturn(docConnection1);
        when(kobisPage.detailPage(movieCode2)).thenReturn(docConnection2);
        when(kobisPage.detailPage(movieCode3)).thenReturn(docConnection3);

        when(docConnect.responseDoc(docConnection1)).thenReturn(document1);
        when(docConnect.responseDoc(docConnection2)).thenReturn(document2);
        when(docConnect.responseDoc(docConnection3)).thenReturn(document3);
        //when
        String movieTitle = kobisMovieTitleParser.getParsingResult(movieCode1);

        KOBISTitleParsingFailException exception1 = assertThrows(KOBISTitleParsingFailException.class,
                () -> kobisMovieTitleParser.getParsingResult(movieCode2));

        KOBISTitleParsingFailException exception2 = assertThrows(KOBISTitleParsingFailException.class,
                () -> kobisMovieTitleParser.getParsingResult(movieCode3));

        //then
        assertEquals("TEST", movieTitle);
        assertEquals("영화 타이틀 파싱 실패 : Element 가 비어 있음", exception1.getMessage());
        assertEquals("영화 타이틀 파싱 실패 : Element 가 null", exception2.getMessage());
    }

}
