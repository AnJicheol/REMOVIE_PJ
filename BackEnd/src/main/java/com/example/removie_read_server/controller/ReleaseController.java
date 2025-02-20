package com.example.removie_read_server.controller;


import com.example.removie_read_server.dto.ReleaseDTO;
import com.example.removie_read_server.service.ReleaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/release/info")
public class ReleaseController {
    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping
    public ResponseEntity<List<ReleaseDTO>> getReleaseInfo(){
        return ResponseEntity.ok(releaseService.getReleaseDTOList());
    }

}
