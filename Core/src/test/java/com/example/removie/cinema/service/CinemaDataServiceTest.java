package com.example.removie.cinema.service;

import com.example.removie.cinema.CinemaData;
import com.example.removie.kobis.cinema.KOBISCinemaElement;
import com.example.removie.kobis.cinema.KOBISCinemaDataParser;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CinemaDataServiceTest {

    @InjectMocks private CinemaDataServiceImpl cinemaDataService;
    @Mock private KOBISCinemaDataParser KOBISCinemaDataParser;
    @Mock private KOBISCinemaElement kobisCinemaElement;



    @Test
    public void Test(){
        //given
        String movieCode = "20240737";
        Elements elements = mock(Elements.class);
        Element element   = mock(Element.class);
        CinemaData cinemaData = mock(CinemaData.class);

        when(kobisCinemaElement.getCinemaCodeElements(movieCode)).thenReturn(elements);
        when(KOBISCinemaDataParser.extractCinemaData(element)).thenReturn(cinemaData);
        when(elements.stream()).thenReturn(Stream.of(element));
        when(cinemaData.isINVALID()).thenReturn(false);

        //when
        List<CinemaData> cinemaDataList = cinemaDataService.getCinemaData(movieCode);

        //then
        assertEquals(1, cinemaDataList.size());
        assertEquals(cinemaData, cinemaDataList.get(0));
    }

}
