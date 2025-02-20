package com.example.removie_read_server.controller;


import com.example.removie_read_server.UpdateTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/update")
public class UpdateTriggerController {
    private final UpdateTrigger updateTrigger;

    @Autowired
    public UpdateTriggerController(UpdateTrigger updateTrigger) {
        this.updateTrigger = updateTrigger;
    }

    @PostMapping
    public ResponseEntity<Void> update() {
        updateTrigger.updateProcess();
        return ResponseEntity.noContent().build();
    }

}
