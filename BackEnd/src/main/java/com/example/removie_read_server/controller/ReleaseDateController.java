package com.example.removie_read_server.controller;


import com.example.removie_read_server.dto.ReleaseDateProjection;
import com.example.removie_read_server.service.ReleaseDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie/info")
public class ReleaseDateController {
    private final ReleaseDateService releaseDateService;

    @Autowired
    public ReleaseDateController(ReleaseDateService releaseDateService) {
        this.releaseDateService = releaseDateService;
    }

    @GetMapping("/date/{movieCode}")
    public ResponseEntity<List<ReleaseDateProjection>> getMovieDate(@PathVariable String movieCode){
        return ResponseEntity.ok(releaseDateService.getReleaseDate(movieCode));
    }
}
