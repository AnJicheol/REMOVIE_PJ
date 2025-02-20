package com.example.removie.cinema.service;


import com.example.removie.cinema.CinemaData;
import com.example.removie.cinema.CinemaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
public class CinemaServiceTest {

    @InjectMocks private CinemaServiceImpl cinemaService;
    @Mock private CinemaDataService cinemaDataService;


    @Test
    public void getCinemaEntityListTest(){
        //given
        List<String> movieCodeList = List.of("20240737", "20247693", "20212352");
        List<CinemaData> cinemaData1 = new ArrayList<>();
        List<CinemaData> cinemaData2 = new ArrayList<>();
        List<CinemaData> cinemaData3 = new ArrayList<>();

        doAnswer(invocation ->{
            String movieCode = invocation.getArgument(0);

            return switch (movieCode) {
                case "20240737" -> cinemaData1;
                case "20247693" -> cinemaData2;
                case "20212352" -> cinemaData3;
                default -> null;
            };
        }).when(cinemaDataService).getCinemaData(anyString());

        //when
        List<CinemaEntity> cinemaEntityList = cinemaService.getCinemaEntityList(movieCodeList);

        //then
        assertThat(cinemaEntityList)
                .extracting(CinemaEntity::getMovieCode, CinemaEntity::getCinemaDataList)
                .containsExactlyInAnyOrder(tuple("20240737", cinemaData1),
                        tuple("20247693", cinemaData2),
                        tuple("20212352", cinemaData3));

    }

}
