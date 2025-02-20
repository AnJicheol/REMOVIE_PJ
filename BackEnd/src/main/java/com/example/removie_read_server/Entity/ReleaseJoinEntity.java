package com.example.removie_read_server.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Getter
@Entity
@NoArgsConstructor
public class ReleaseJoinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer version;

    @NotNull
    @Column(name = "ranking")
    private Integer ranking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_code", referencedColumnName = "movie_code", nullable = false)
    private MovieDataEntity movieDataEntity;

    @Builder
    public ReleaseJoinEntity(@NonNull Integer version, @NonNull Integer ranking, MovieDataEntity movieDataEntity) {
        this.version = version;
        this.ranking = ranking;
        this.movieDataEntity = movieDataEntity;
    }

}
