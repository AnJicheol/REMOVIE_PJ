package com.example.removie_read_server.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RankingDTO {
    private String movieCode;
    private Integer insertRanking;

    @Builder
    public RankingDTO(String movieCode, Integer insertRanking) {
        this.movieCode = movieCode;
        this.insertRanking = insertRanking;
    }

    public static RankingDTO of(String movieCode, Integer insertRanking){
        return RankingDTO.builder()
                .movieCode(movieCode)
                .insertRanking(insertRanking)
                .build();
    }
}
