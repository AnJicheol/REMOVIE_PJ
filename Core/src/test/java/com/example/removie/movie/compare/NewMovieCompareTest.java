package com.example.removie.movie.compare;


import com.example.removie.kobis.KOBISMovieDataService;
import com.example.removie.kobis.parser.KOBISMovieDateParser;
import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.compare.result.NewMovieCompareResult;
import com.example.removie.movie.vo.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@SpringBootTest
public class NewMovieCompareTest {

    @InjectMocks NewMovieCompareImpl newMovieCompare;
    @Mock KOBISMovieDateParser kobisMovieDateParser;
    @Mock KOBISMovieDataService kobisMovieDataService;

    @Test
    public void NewMovieCompareResultTest(){
        //given

        NewReleaseMap newReleaseMap = CompareTestDataFactory.createNewReleaseMap();
        List<CurrentMovieVO> currentMovieVOList = CompareTestDataFactory.createCurrentMovieVOList();

        when(kobisMovieDateParser.getParsingResult(anyString())).thenReturn(new ReleaseDate("movieCode", new ArrayList<>()));
        when(kobisMovieDataService.getMovieData(anyString())).thenAnswer(invocation -> new MovieData("title", invocation.getArgument(0), "url", "info"));

        //when
        NewMovieCompareResult newMovieCompareResult = newMovieCompare.compare(newReleaseMap, currentMovieVOList);

        List<NewMovieVO> newMovieVOList = newMovieCompareResult.getNewMovieList();
        List<ReleaseDate> releaseDateList = newMovieCompareResult.getReleaseDateList();
        List<MovieData> movieDataList = newMovieCompareResult.getNewMovieDataList();


        //then
        assertEquals(newMovieVOList.size(), movieDataList.size());
        assertEquals("movieCode", releaseDateList.getFirst().getMovieCode());

        for(int i = 0; i < 2; i++){
            assertEquals(
                    newMovieVOList.getFirst().getMovieCode(),
                    movieDataList.getFirst().getMovieCode()
            );
        }
    }

}
