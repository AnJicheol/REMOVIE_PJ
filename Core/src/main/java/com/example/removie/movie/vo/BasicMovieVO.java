package com.example.removie.movie.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class BasicMovieVO{

    @NotBlank
    private final String movieCode;


    @NotNull
    private final Integer ranking;

    @Builder
    public BasicMovieVO(@NonNull String movieCode, @NonNull Integer ranking) {
        this.movieCode = movieCode;
        this.ranking = ranking;
    }

}
