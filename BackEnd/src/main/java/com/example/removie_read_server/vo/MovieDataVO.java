package com.example.removie_read_server.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class MovieDataVO {

    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String movieCode;

    private final String posterIMG;
    private final String info;
    
    @Builder
    public MovieDataVO(@NonNull String title, @NonNull String movieCode, String posterIMG, String info) {
        this.title = title;
        this.movieCode = movieCode;
        this.posterIMG = posterIMG;
        this.info = info;
    }
}
