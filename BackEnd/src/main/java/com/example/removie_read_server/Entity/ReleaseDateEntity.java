package com.example.removie_read_server.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class ReleaseDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String movieCode;
    @NotNull
    private LocalDate releaseDate;

    public ReleaseDateEntity(String movieCode, LocalDate releaseDate) {
        this.movieCode = movieCode;
        this.releaseDate = releaseDate;
    }
}
