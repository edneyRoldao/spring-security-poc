package com.edntisolutions.aouth.api.controller;

import com.edntisolutions.aouth.api.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("players")
public class PlayerController {

    @GetMapping
    public ResponseEntity<List<Player>> listPlayers() {
        return ResponseEntity.ok(Player.getMockPlayers());
    }

}
