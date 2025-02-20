package com.example.removie.kobis.cinema;

import com.example.removie.cinema.CinemaData;
import com.example.removie.cinema.exception.CinemaParsingFailException;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class KOBISKOBISCinemaDataParserTest {

    @Test
    public void extractCinemaDataTest(){
        //given
        KOBISCinemaDataParser kobisCinemaDataParser = new KOBISCinemaDataParser();
        Element element1 = new Element("onclick=\"fn_theaNmClick(this, '20240737', 'B09', 'Y')");
        Element element2 = new Element("onclick=\"fn_theaNmClick(this, '20240737', 'TEST', 'Y')");
        Element element3 = new Element("TEST - TEST");

        //when
        CinemaData cinemaData1 = kobisCinemaDataParser.extractCinemaData(element1);
        CinemaData cinemaData2 = kobisCinemaDataParser.extractCinemaData(element2);

        CinemaParsingFailException exception = assertThrows(CinemaParsingFailException.class, () -> {
            kobisCinemaDataParser.extractCinemaData(element3);
        });


        // then
        assertEquals(cinemaData1.getCinemaName(), "씨네큐");

        assertTrue(cinemaData2.isINVALID());
        assertEquals("상영관 코드 정규식 파싱 실패 : 패턴 매칭 실패", exception.getMessage());

    }


    @Test
    public void regexTest(){
        String test = "<a href=\"#\" id=\"button1\" name=\"button1\" onclick=\"fn_theaNmClick(this, '20246939', 'B01', 'P'); return false;\">47개 영화상영관</a>";
        Pattern CINEMA_CODE_REGEX_PATTERN = Pattern.compile("onclick=\"fn_theaNmClick\\(this, '.*?', '(.*?)', '.*?'\\)");
        Matcher matcher = CINEMA_CODE_REGEX_PATTERN.matcher(test);

        if(matcher.find()){
            System.out.println(matcher.group(1));
        }

    }

}
