package com.example.removie.kobis.movie.parser;

import com.example.removie.document.DocConnect;
import com.example.removie.document.DocConnection;
import com.example.removie.document.kobis.KOBISPage;
import com.example.removie.kobis.parser.KOBISMovieIMGUriParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class KOBISMovieIMGUriParserTest {

    @InjectMocks KOBISMovieIMGUriParser kobisMovieIMGUriParser;
    @Mock DocConnect docConnect;
    @Mock KOBISPage kobisPage;

    @Test
    public void getMovieImgURLTest(){
        //given

        Document document1 = Jsoup.parse("""
                <html>
                  <body>
                    <a class="fl thumb" href="/test.jpg">
                      <img src="test test" alt="Movie Poster">
                    </a>
                  </body>
                </html>""");

        Document document2 = Jsoup.parse("TEST - TEST");

        String movieCode1 = "20240737";
        String movieCode2 = "로그 체크";

        DocConnection docConnection1 = mock(DocConnection.class);
        DocConnection docConnection2 = mock(DocConnection.class);

        when(kobisPage.detailPage(movieCode1)).thenReturn(docConnection1);
        when(kobisPage.detailPage(movieCode2)).thenReturn(docConnection2);

        when(docConnect.responseDoc(docConnection1)).thenReturn(document1);
        when(docConnect.responseDoc(docConnection2)).thenReturn(document2);

        //when
        String url1 = kobisMovieIMGUriParser.getParsingResult(movieCode1);
        String url2 = kobisMovieIMGUriParser.getParsingResult(movieCode2);

        //then
        assertEquals("https://www.kobis.or.kr/test.jpg", url1);
        assertNull(url2);
    }


}
