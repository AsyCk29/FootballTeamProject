package com.team.controllers;

import com.team.entities.Player;
import com.team.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



/**
 * @author Asiye Çak
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/player")
public class PlayerRestController {
    final PlayerService playerService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody  Player player) {
        return playerService.playerRegister(player);

    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  Player player) {
        return playerService.login(player);
    }
    @GetMapping("/deleteAllPlayer")
    public ResponseEntity deleteAllPlayer() {
        return playerService.deleteAllPlayer();
    }
    @GetMapping("/listAllPlayer")
    public ResponseEntity listAllPlayer() {
        return playerService.listAllPlayer();
    }


}
