package com.example.removie_read_server.dto;

import com.example.removie_read_server.vo.LatestVersion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class MovieSyncDTO {
    private final List<NewMovieResponse> newMovie;
    private final List<RetireMovieProjection> retireMovie;
    private final List<RankingDTO> rankingSync;
    private final LatestVersion latestVersion;
    private final Boolean valid;

    private MovieSyncDTO(Builder builder) {
        this.newMovie = builder.updateMovie;
        this.retireMovie = builder.retireMovie;
        this.rankingSync = builder.rankingSync;
        this.latestVersion = builder.latestVersion;
        this.valid = builder.valid;
    }

    public static class Builder {
        private List<NewMovieResponse> updateMovie = Collections.emptyList(); 
        private List<RetireMovieProjection> retireMovie = Collections.emptyList();
        private List<RankingDTO> rankingSync = Collections.emptyList();
        private LatestVersion latestVersion;
        private Boolean valid;

        public Builder updateMovie(List<NewMovieResponse> updateMovie) {
            this.updateMovie = updateMovie;
            return this;
        }

        public Builder retireMovie(List<RetireMovieProjection> retireMovie) {
            this.retireMovie = retireMovie;
            return this;
        }

        public Builder rankingSync(List<RankingDTO> rankingSync) {
            this.rankingSync = rankingSync;
            return this;
        }

        public Builder latestVersion(LatestVersion latestVersion) {
            this.latestVersion = latestVersion;
            return this;
        }

        public Builder valid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public MovieSyncDTO build() {
            if(this.updateMovie == null) this.updateMovie = new ArrayList<>();
            if(this.retireMovie == null) this.retireMovie = new ArrayList<>();
            if(this.rankingSync == null) this.rankingSync = new ArrayList<>();
            if(this.latestVersion == null) this.latestVersion = LatestVersion.empty();
            if(this.valid == null) this.valid = true;

            return new MovieSyncDTO(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}
