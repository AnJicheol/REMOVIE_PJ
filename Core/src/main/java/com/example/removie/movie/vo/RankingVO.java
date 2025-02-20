package com.example.removie.movie.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class RankingVO{

    @NotBlank
    private final String MovieCode;

    @NotNull
    private final Integer insertRanking;

    public RankingVO(@NonNull String movieCode, @NonNull Integer insertRanking){
        this.MovieCode = movieCode;
        this.insertRanking = insertRanking;
    }

    public static RankingVO changeOf(@NonNull String movieCode, @NonNull Integer ranking) {
        return new RankingVO(movieCode, ranking);
    }
}
