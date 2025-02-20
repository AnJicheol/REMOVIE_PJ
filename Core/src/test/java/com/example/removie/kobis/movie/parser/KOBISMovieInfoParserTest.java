package com.example.removie.kobis.movie.parser;


import com.example.removie.document.DocConnect;
import com.example.removie.document.DocConnection;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.parser.KOBISMovieInfoParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class KOBISMovieInfoParserTest {


    @InjectMocks public KOBISMovieInfoParser kobisMovieInfoParser;
    @Mock DocConnect docConnect;
    @Mock KOBISPage kobisPage;

    @Test
    public void getMovieInfoTest(){
        //given

        Document document1 = Jsoup.parse("""
                <html>
                  <body>
                    <dl>
                      <dt>영화제목</dt>
                      <dt>요약정보</dt>
                      <dd>test</dd>
                    </dl>
                  </body>
                </html>""");

        Document document2 = Jsoup.parse("TEST - TEST");

        String movieCode1 = "doc1_movieCode";
        String movieCode2 = "doc2_movieCode";

        DocConnection docConnection1 = mock(DocConnection.class);
        DocConnection docConnection2 = mock(DocConnection.class);

        when(kobisPage.detailPage(movieCode1)).thenReturn(docConnection1);
        when(kobisPage.detailPage(movieCode2)).thenReturn(docConnection2);

        when(docConnect.responseDoc(docConnection1)).thenReturn(document1);
        when(docConnect.responseDoc(docConnection2)).thenReturn(document2);

        //when

        String info1 = kobisMovieInfoParser.getParsingResult(movieCode1);
        String info2 = kobisMovieInfoParser.getParsingResult(movieCode2);

        //then

        assertEquals("test", info1);
        assertEquals("", info2);

    }


}
