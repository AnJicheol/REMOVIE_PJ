package com.example.removie_read_server.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class NewMovieResponse {
    private final String movieCode;
    private final Integer ranking;
    private final boolean removie;

    @Builder
    public NewMovieResponse(String movieCode, Integer ranking, boolean removie) {
        this.movieCode = movieCode;
        this.ranking = ranking;
        this.removie = removie;
    }
}
