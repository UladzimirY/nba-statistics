package com.skyhawk.game.controller;

import com.skyhawk.game.dto.GameDto;
import com.skyhawk.game.dto.GameStatisticsDto;
import com.skyhawk.game.exception.GameNotFoundException;
import com.skyhawk.game.service.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameDto> createGame(@RequestBody @Valid GameDto game) {
        return new ResponseEntity<>(gameService.createGame(game), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGame(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.getGame(id));
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<GameStatisticsDto>> getPlayerStatistics(@PathVariable Long playerId) {
        return ResponseEntity.ok(gameService.getPlayerStatistics(playerId));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<GameStatisticsDto>> getTeamStatistics(@PathVariable Long teamId) {
        return ResponseEntity.ok(gameService.getTeamStatistics(teamId));
    }
}
