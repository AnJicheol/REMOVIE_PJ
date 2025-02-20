package com.example.removie.document.kobis;

import com.example.removie.document.DocConnection;
import com.example.removie.document.POSTConnection;
import com.example.removie.kobis.KOBISCSRFToken;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class KOBISPage {
    private final KOBISCSRFToken kobiscsrfToken;

    public KOBISPage(KOBISCSRFToken kobiscsrfToken) {
        this.kobiscsrfToken = kobiscsrfToken;
    }

    @Cacheable(value = "cinemaPage", key = "#movieCode")
    public DocConnection cinemaPage(String movieCode){
        return POSTConnection.of(Jsoup.connect(KOBIS.KOBIS_MAIN_CINEMA_URI.getValue()).
                KOBIS 정보가 포함되어 제거하였습니다.
        );
    }

    @Cacheable(value = "removieDatePage", key = "#movieCode")
    public DocConnection removieDatePage(String movieCode){
        return POSTConnection.of(Jsoup.connect(KOBIS.KOBIS_MAIN_CINEMA_URI.getValue()).
                KOBIS 정보가 포함되어 제거하였습니다.
        );
    }

    @Cacheable(value = "detailPage", key = "#movieCode")
    public DocConnection detailPage(String movieCode){
        return POSTConnection.of(Jsoup.connect(KOBIS.KOBIS_MOVIE_DETAIL_INFO_URI.getValue()).
                KOBIS 정보가 포함되어 제거하였습니다.
        );
    }

    @Cacheable(value = "mainPage")
    public DocConnection mainPage(){
        return POSTConnection.of(Jsoup.connect(KOBIS.KOBIS_MAIN_URI.getValue()).
                KOBIS 정보가 포함되어 제거하였습니다.
        );
    }


}
