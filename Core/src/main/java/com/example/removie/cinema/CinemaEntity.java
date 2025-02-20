package com.example.removie.cinema;


import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import java.util.List;


@Getter
@NoArgsConstructor
@RedisHash("CurrentCinema")
@ToString
public class CinemaEntity {

    @Id
    @NotBlank
    private String movieCode;

    @NotBlank
    private List<CinemaData> cinemaDataList;

    public CinemaEntity(@NonNull String movieCode, @NonNull List<CinemaData> cinemaDataList) {
        this.movieCode = movieCode;
        this.cinemaDataList = cinemaDataList;
    }
}
