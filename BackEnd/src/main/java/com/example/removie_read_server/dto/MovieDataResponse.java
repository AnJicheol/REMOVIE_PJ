package com.example.removie_read_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;


@Getter
public class MovieDataResponse {
    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String movieCode;
    private final String posterIMG;
    private final String info;


    @Builder
    public MovieDataResponse(@NonNull String title, @NonNull String movieCode, String posterIMG, String info) {
        this.title = title;
        this.movieCode = movieCode;
        this.posterIMG = posterIMG;
        this.info = info;
    }
}
