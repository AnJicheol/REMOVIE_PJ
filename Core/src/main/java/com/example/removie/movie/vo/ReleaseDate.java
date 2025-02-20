package com.example.removie.movie.vo;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReleaseDate {

    @NotBlank
    private final String movieCode;
    private final List<LocalDate> releaseDate;

    public ReleaseDate(@NonNull String movieCode, @NonNull List<LocalDate> releaseDate) {
        this.movieCode = movieCode;
        this.releaseDate = releaseDate;
    }

    public int getReleaseDateSize(){
        return releaseDate.size();
    }
}
