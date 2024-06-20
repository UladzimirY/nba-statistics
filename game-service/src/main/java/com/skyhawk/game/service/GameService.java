package com.skyhawk.game.service;

import com.skyhawk.game.dto.GameDto;
import com.skyhawk.game.dto.GameStatisticsDto;
import com.skyhawk.game.exception.GameNotFoundException;
import com.skyhawk.game.mapper.GameMapper;
import com.skyhawk.game.mapper.GameStatisticsMapper;
import com.skyhawk.game.model.Game;
import com.skyhawk.game.model.GameStatistics;
import com.skyhawk.game.repository.GameRepository;
import com.skyhawk.game.repository.GameStatisticsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final GameStatisticsRepository gameStatisticsRepository;

    @Autowired
    public GameService(GameRepository gameRepository, GameStatisticsRepository gameStatisticsRepository) {
        this.gameRepository = gameRepository;
        this.gameStatisticsRepository = gameStatisticsRepository;
    }

    public GameDto createGame(GameDto gameDTO) {
        var game = GameMapper.INSTANCE.gameDtoToGame(gameDTO);
        game = gameRepository.save(game);
        return GameMapper.INSTANCE.gameToGameDto(game);
    }

    public GameDto getGame(Long id) {
        var game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException("Game not found with id: " + id));
        return GameMapper.INSTANCE.gameToGameDto(game);
    }

    public List<GameStatisticsDto> getPlayerStatistics(Long playerId) {
        return getStatisticsDto(gameStatisticsRepository.findByPlayerId(playerId));
    }

    public List<GameStatisticsDto> getTeamStatistics(Long teamId) {
        return getStatisticsDto(gameStatisticsRepository.findByTeamId(teamId));
    }

    private List<GameStatisticsDto> getStatisticsDto(List<GameStatistics> statistics) {
        if (statistics == null || statistics.isEmpty()) {
            return List.of();
        }
        return statistics.stream()
                .map(GameStatisticsMapper.INSTANCE::gameStatisticsToGameStatisticsDto)
                .collect(Collectors.toList());
    }

}
