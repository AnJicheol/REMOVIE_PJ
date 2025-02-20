package com.example.removie.movie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Optional;


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

    @Column(nullable = false)
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

    public Optional<String> getPosterIMG(){
        return Optional.ofNullable(posterIMG);
    }

    public Optional<String> getInfo(){
        return Optional.ofNullable(info);
    }
}
