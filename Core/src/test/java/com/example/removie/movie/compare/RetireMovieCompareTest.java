package com.example.removie.movie.compare;

import com.example.removie.movie.NewReleaseMap;
import com.example.removie.movie.compare.result.RetireMovieCompareResult;
import com.example.removie.movie.vo.CurrentMovieVO;
import com.example.removie.movie.vo.RetireMovieVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RetireMovieCompareTest {

      /*
    NewRelease
        ("MOV001", 1);
        ("MOV002", 2);
        ("MOV003", 5);
        ("MOV004", 4);
        ("MOV005", 3);

    CurrentList
        ("MOV001", 1),
        ("MOV003", 3),
        ("MOV005", 5),
        ("MOV006", 6),
        ("MOV007", 7)
    * */



    @Test
    public void retireCompareTest(){
        //given
        NewReleaseMap newReleaseMap = CompareTestDataFactory.createNewReleaseMap();
        List<CurrentMovieVO> currentMovieVOList = CompareTestDataFactory.createCurrentMovieVOList();
        RetireMovieCompareImpl retireMovieCompare = new RetireMovieCompareImpl();

        //when
        RetireMovieCompareResult result = retireMovieCompare.compare(newReleaseMap, currentMovieVOList);
        List<RetireMovieVO> retireMovieVOList = result.getRetireMovieVOList();

        //then
        assertEquals(2, retireMovieVOList.size());
        assertThat(retireMovieVOList)
                .extracting(RetireMovieVO :: getMovieCode)
                .containsExactly("MOV006", "MOV007");



    }
}
