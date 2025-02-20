package com.example.removie_read_server.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReleaseDTO {
    private final String movieCode;
    private final boolean removie;

    @Builder
    public ReleaseDTO(String movieCode, boolean removie) {
        this.movieCode = movieCode;
        this.removie = removie;
    }
}
