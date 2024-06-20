package com.skyhawk.player.controller;

import com.skyhawk.player.dto.PlayerDto;
import com.skyhawk.player.exception.PlayerNotFoundException;
import com.skyhawk.player.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody @Valid PlayerDto player) {
        playerService.createPlayer(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDto> getPlayer(@PathVariable Long id) throws PlayerNotFoundException {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlayerDto> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }

}

