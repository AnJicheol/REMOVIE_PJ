package com.example.removie_read_server.dto;


import com.example.removie_read_server.vo.CinemaData;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;

@Getter
public class CinemaDTO {

    private final List<CinemaData> cinemaDataList;

    public CinemaDTO(@NonNull List<CinemaData> cinemaDataList) {
        this.cinemaDataList = cinemaDataList;
    }
}
