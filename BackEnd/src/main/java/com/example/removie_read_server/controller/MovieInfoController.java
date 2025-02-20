package com.example.removie_read_server.controller;


import com.example.removie_read_server.dto.MovieDataResponse;
import com.example.removie_read_server.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movie/info")
public class MovieInfoController {
    private final MovieService movieService;

    @Autowired
    public MovieInfoController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public ResponseEntity<List<MovieDataResponse>> getMovieInfo(@RequestParam List<String> movieCode){
        return ResponseEntity.ok(movieService.getMovieInfoList(movieCode));
    }

    @GetMapping("page")
    public ResponseEntity<List<MovieDataResponse>> getMovieInfo(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit) {

        return ResponseEntity.ok(movieService.getMovieInfoList(offset, limit));
    }
}


