package com.example.removie.cinema;

import java.util.HashMap;
import java.util.Map;


/**
 * 영화관 정보를 제공하는 유틸리티 클래스입니다.
 *
 * @author An_Jicheol
 * @version 1.0
 */
public class CinemaInfoProvider {
    private static Map<String, CinemaData> cinemaMap;

    public static CinemaData getMovieInfo(String cinemaCode) {
        if (cinemaMap == null) {
            initializeMap();
        }
        return cinemaMap.getOrDefault(cinemaCode,CinemaData.INVALID());
    }

    private static void initializeMap() {
        cinemaMap = new HashMap<>();
        cinemaMap.put("A12", new CinemaData("유유코리아", "https://petitecine.com"));
        cinemaMap.put("A07", new CinemaData("현대예술관시네마", "https://www.hhi.co.kr"));
        cinemaMap.put("A08", new CinemaData("문화인", "https://www.moonhwain.com/"));
        cinemaMap.put("A09", new CinemaData("영화의전당", "https://www.dureraum.org"));
        cinemaMap.put("A10", new CinemaData("디트릭스", "https://www.dtryx.com"));
        cinemaMap.put("A11", new CinemaData("더브이엑스", "https://www.awclub.co.kr"));
        cinemaMap.put("A14", new CinemaData("영화공장", ""));
        cinemaMap.put("A15", new CinemaData("에스아이티랩", "https://www.tinyticket.net/"));
        cinemaMap.put("B01", new CinemaData("CGV", "https://www.cgv.co.kr"));
        cinemaMap.put("B02", new CinemaData("롯데시네마", "https://www.lottecinema.co.kr/"));
        cinemaMap.put("B07", new CinemaData("메가박스", "https://www.megabox.co.kr"));
        cinemaMap.put("B08", new CinemaData("CGV", "https://www.cgv.co.kr"));
        cinemaMap.put("B09", new CinemaData("씨네큐", "https://www.cineq.co.kr"));
    }
}
