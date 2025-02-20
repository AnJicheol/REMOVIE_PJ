package com.example.removie_read_server.controller;

import com.example.removie_read_server.dto.CinemaDTO;
import com.example.removie_read_server.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{movieCode}")
    public ResponseEntity<CinemaDTO> getCinemaList(@PathVariable String movieCode){
        return ResponseEntity.ok(cinemaService.getCinemaList(movieCode));
    }

}
