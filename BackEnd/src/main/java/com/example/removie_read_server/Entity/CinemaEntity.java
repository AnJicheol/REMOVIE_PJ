package com.example.removie_read_server.Entity;


import com.example.removie_read_server.vo.CinemaData;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
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
