package com.example.removie.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Entity
@NoArgsConstructor
public class ReleaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer version;

    @NotNull
    @NotEmpty
    private String movieCode;

    @NotNull
    private Integer ranking;

    @Builder
    public ReleaseEntity(@NonNull Integer version, @NonNull String movieCode, @NonNull Integer ranking) {
        this.version = version;
        this.movieCode = movieCode;
        this.ranking = ranking;
    }

}
