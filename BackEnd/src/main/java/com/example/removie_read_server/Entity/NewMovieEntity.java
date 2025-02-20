package com.example.removie_read_server.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Getter
@NoArgsConstructor
public class NewMovieEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private Integer version;

    @Column(nullable = false)
    @NotNull
    private Integer ranking;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String movieCode;

    public NewMovieEntity(@NonNull Integer version, @NonNull Integer ranking, @NonNull String movieCode) {
        this.version = version;
        this.ranking = ranking;
        this.movieCode = movieCode;
    }

}
