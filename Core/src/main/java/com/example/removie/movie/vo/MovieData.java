package com.example.removie.movie.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;


@Builder
@Getter
@ToString
public class MovieData {

    @NotBlank
    private final String title;

    @NotBlank
    private final String movieCode;
    private final String posterIMG;
    private final String info;


    public MovieData(@NonNull String title, @NonNull String movieCode, String posterIMG, String info) {
        this.title = title;
        this.movieCode = movieCode;
        this.posterIMG = posterIMG;
        this.info = info;
    }

}
