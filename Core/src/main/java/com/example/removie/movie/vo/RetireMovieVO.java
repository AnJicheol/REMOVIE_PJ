package com.example.removie.movie.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RetireMovieVO{

    @NotBlank
    private final String movieCode;

    public RetireMovieVO(@NonNull String movieCode){
        this.movieCode = movieCode;
    }
}
