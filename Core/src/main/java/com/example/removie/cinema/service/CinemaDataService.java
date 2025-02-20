package com.example.removie.cinema.service;

import com.example.removie.cinema.CinemaData;

import java.util.List;

public interface CinemaDataService {
    List<CinemaData> getCinemaData(String movieCode);
}
