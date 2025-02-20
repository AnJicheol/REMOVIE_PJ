package com.example.removie_read_server.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private Integer ranking;

    @NotBlank
    @Column(name = "movie_code")
    private String movieCode;

    public ReleaseEntity(Integer version, Integer ranking, String movieCode) {
        this.version = version;
        this.ranking = ranking;
        this.movieCode = movieCode;
    }
}
