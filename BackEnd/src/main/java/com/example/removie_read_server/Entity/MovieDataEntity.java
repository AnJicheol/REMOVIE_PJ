package com.example.removie_read_server.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@Entity
@NoArgsConstructor
public class MovieDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotEmpty
    private String title;

    @Column(nullable = false, name = "movie_code")
    @NotNull
    @NotEmpty
    private String movieCode;

    private String posterIMG;
    private String info;


    @Builder
    public MovieDataEntity(@NonNull String title, @NonNull String movieCode,String posterIMG, String info) {
        this.title = title;
        this.movieCode = movieCode;
        this.posterIMG = posterIMG;
        this.info = info;
    }
}
