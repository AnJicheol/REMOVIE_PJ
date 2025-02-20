package com.example.removie.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor
public class RankingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Integer version;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String movieCode;

    @Column(nullable = false)
    @NotNull
    private Integer insertRanking;

    public RankingEntity(@NonNull Integer version, @NonNull String movieCode, @NonNull Integer insertRanking) {
        this.version = version;
        this.movieCode = movieCode;
        this.insertRanking = insertRanking;
    }

}
