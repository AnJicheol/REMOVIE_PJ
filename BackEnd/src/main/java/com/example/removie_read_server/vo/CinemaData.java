package com.example.removie_read_server.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class CinemaData {

    @NotBlank
    private  String cinemaName;

    @NotNull
    private  String cinemaUri;

    public CinemaData(@NonNull String cinemaName, @NonNull String cinemaUri) {
        this.cinemaName = cinemaName;
        this.cinemaUri = cinemaUri;
    }
}
