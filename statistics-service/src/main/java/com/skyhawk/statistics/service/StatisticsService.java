package com.skyhawk.statistics.service;

import com.skyhawk.statistics.client.GameClient;
import com.skyhawk.statistics.dto.PlayerStatDto;
import com.skyhawk.statistics.dto.TeamStatDto;
import com.skyhawk.statistics.exception.NotPlayerStatisticsAvailable;
import com.skyhawk.statistics.exception.NotTeamStatisticsAvailable;
import com.skyhawk.statistics.model.GameStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;

@Service
public class StatisticsService {

    private final GameClient gameClient;

    @Autowired
    public StatisticsService(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public PlayerStatDto getPlayerSeasonAverage(Long playerId) {
        return getStatistics(
                playerId,
                gameClient::getPlayerStatistics,
                GameStat::getPoints,
                GameStat::getRebounds,
                GameStat::getAssists,
                PlayerStatDto::new,
                () -> new NotPlayerStatisticsAvailable("Player statistics not found with playerId: " + playerId)
        );
    }

    public TeamStatDto getTeamSeasonAverage(Long teamId) {
        return getStatistics(
                teamId,
                gameClient::getTeamStatistics,
                GameStat::getPoints,
                GameStat::getRebounds,
                GameStat::getAssists,
                TeamStatDto::new,
                () -> new NotTeamStatisticsAvailable("Team statistics not found with teamId: " + teamId)
        );
    }

    private <T, E extends RuntimeException> T getStatistics(
            Long id,
            Function<Long, List<GameStat>> statisticsFunction,
            ToDoubleFunction<GameStat> pointsMapper,
            ToDoubleFunction<GameStat> reboundsMapper,
            ToDoubleFunction<GameStat> assistsMapper,
            StatisticDtoCreator<Long, T> dtoCreator,
            Supplier<E> exceptionSupplier
    ) {
        List<GameStat> gameStats = statisticsFunction.apply(id);

        if (gameStats.isEmpty()) {
            throw exceptionSupplier.get();
        }

        double averagePoints = calculateAverage(gameStats, pointsMapper);
        double averageRebounds = calculateAverage(gameStats, reboundsMapper);
        double averageAssists = calculateAverage(gameStats, assistsMapper);

        return dtoCreator.create(id, averagePoints, averageRebounds, averageAssists);
    }

    private double calculateAverage(List<GameStat> stats, ToDoubleFunction<GameStat> mapper) {
        return stats.stream()
                .mapToDouble(mapper)
                .average()
                .orElse(0.0);
    }

    @FunctionalInterface
    private interface StatisticDtoCreator<ID, T> {
        T create(ID id, double avgPoints, double avgRebounds, double avgAssists);
    }
}
