package com.example.removie_read_server.controller;


import com.example.removie_read_server.dto.MovieSyncDTO;
import com.example.removie_read_server.service.MovieInfoSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie/info/sync")
public class MovieInfoSyncController {
    private final MovieInfoSyncService movieInfoSyncService;

    @Autowired
    public MovieInfoSyncController(MovieInfoSyncService movieInfoSyncService) {
        this.movieInfoSyncService = movieInfoSyncService;
    }

    @GetMapping
    public ResponseEntity<MovieSyncDTO> getMovieSyncData(@RequestParam Integer version){
        return ResponseEntity.ok(movieInfoSyncService.getMovieSyneData(version));
    }
}
