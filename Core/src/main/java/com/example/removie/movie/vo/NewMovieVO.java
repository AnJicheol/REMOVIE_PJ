package com.example.removie.movie.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class NewMovieVO {

    @NotBlank
    private final String movieCode;

    @NotNull
    private final Integer ranking;


    public NewMovieVO(@NonNull String movieCode, @NonNull Integer ranking){
        this.movieCode = movieCode;
        this.ranking = ranking;
    }


}
