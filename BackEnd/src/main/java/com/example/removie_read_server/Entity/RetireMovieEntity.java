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
public class RetireMovieEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String movieCode;

    @Column(nullable = false)
    @NotNull
    private Integer version;

    public RetireMovieEntity(@NonNull String movieCode, @NonNull Integer version) {
        this.movieCode = movieCode;
        this.version = version;
    }

}
