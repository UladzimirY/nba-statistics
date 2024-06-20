package com.skyhawk.statistics.service;

import com.skyhawk.statistics.client.GameClient;
import com.skyhawk.statistics.dto.PlayerStatDto;
import com.skyhawk.statistics.dto.TeamStatDto;
import com.skyhawk.statistics.exception.NotPlayerStatisticsAvailable;
import com.skyhawk.statistics.exception.NotTeamStatisticsAvailable;
import com.skyhawk.statistics.model.GameStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    @Mock
    private GameClient gameClient;

    @InjectMocks
    private StatisticsService statisticsService;

    private List<GameStat> playerGameStats;
    private List<GameStat> teamGameStats;

    @BeforeEach
    void setUp() {
        playerGameStats = new ArrayList<>();
        playerGameStats.add(createGameStat(1L, 1L, 1L, 20, 10, 5, 2, 1, 3, 2, 30.0f));
        playerGameStats.add(createGameStat(2L, 1L, 1L, 15, 8, 3, 1, 2, 2, 1, 25.0f));

        teamGameStats = new ArrayList<>();
        teamGameStats.add(createGameStat(1L, null, 1L, 80, 40, 20, 5, 4, 10, 5, 240.0f));
        teamGameStats.add(createGameStat(2L, null, 1L, 75, 35, 18, 3, 2, 8, 4, 230.0f));
    }

    private GameStat createGameStat(Long gameId, Long playerId, Long teamId, int points, int rebounds, int assists,
                                    int steals, int blocks, int fouls, int turnovers, float minutesPlayed) {
        GameStat gameStat = new GameStat();
        gameStat.setGameId(gameId);
        gameStat.setPlayerId(playerId);
        gameStat.setTeamId(teamId);
        gameStat.setPoints(points);
        gameStat.setRebounds(rebounds);
        gameStat.setAssists(assists);
        gameStat.setSteals(steals);
        gameStat.setBlocks(blocks);
        gameStat.setFouls(fouls);
        gameStat.setTurnovers(turnovers);
        gameStat.setMinutesPlayed(minutesPlayed);
        return gameStat;
    }

    @Test
    void testGetPlayerSeasonAverage() throws NotPlayerStatisticsAvailable {
        when(gameClient.getPlayerStatistics(1L)).thenReturn(playerGameStats);

        PlayerStatDto playerStatDto = statisticsService.getPlayerSeasonAverage(1L);

        assertNotNull(playerStatDto);
        assertEquals(17.5, playerStatDto.averagePoints());
        assertEquals(9.0, playerStatDto.averageRebounds());
        assertEquals(4.0, playerStatDto.averageAssists());

        verify(gameClient, times(1)).getPlayerStatistics(1L);
    }

    @Test
    void testGetTeamSeasonAverage() throws NotTeamStatisticsAvailable {
        when(gameClient.getTeamStatistics(1L)).thenReturn(teamGameStats);

        TeamStatDto teamStatDto = statisticsService.getTeamSeasonAverage(1L);

        assertNotNull(teamStatDto);
        assertEquals(77.5, teamStatDto.averagePoints());
        assertEquals(37.5, teamStatDto.averageRebounds());
        assertEquals(19.0, teamStatDto.averageAssists());

        verify(gameClient, times(1)).getTeamStatistics(1L);
    }

    @Test
    void testGetPlayerSeasonAverage_PlayerStatsNotFound() {
        when(gameClient.getPlayerStatistics(1L)).thenReturn(new ArrayList<>());

        NotPlayerStatisticsAvailable exception = assertThrows(NotPlayerStatisticsAvailable.class, () -> {
            statisticsService.getPlayerSeasonAverage(1L);
        });

        assertEquals("Player statistics not found with playerId: 1", exception.getMessage());

        verify(gameClient, times(1)).getPlayerStatistics(1L);
    }

    @Test
    void testGetTeamSeasonAverage_TeamStatsNotFound() {
        when(gameClient.getTeamStatistics(1L)).thenReturn(new ArrayList<>());

        NotTeamStatisticsAvailable exception = assertThrows(NotTeamStatisticsAvailable.class, () -> {
            statisticsService.getTeamSeasonAverage(1L);
        });

        assertEquals("Team statistics not found with teamId: 1", exception.getMessage());

        verify(gameClient, times(1)).getTeamStatistics(1L);
    }

    @Test
    void testGetPlayerSeasonAverage_PlayerStatsException() {
        when(gameClient.getPlayerStatistics(1L)).thenThrow(new RuntimeException("Failed to fetch player statistics"));

        String errorDetails = assertThrows(RuntimeException.class, () -> {
            statisticsService.getPlayerSeasonAverage(1L);
        }).getLocalizedMessage();

        assertEquals("Failed to fetch player statistics", errorDetails);

        verify(gameClient, times(1)).getPlayerStatistics(1L);
    }

    @Test
    void testGetTeamSeasonAverage_TeamStatsException() {
        when(gameClient.getTeamStatistics(1L)).thenThrow(new RuntimeException("Failed to fetch team statistics"));

        String errorDetails = assertThrows(RuntimeException.class, () -> {
            statisticsService.getTeamSeasonAverage(1L);
        }).getLocalizedMessage();

        assertEquals("Failed to fetch team statistics", errorDetails);

        verify(gameClient, times(1)).getTeamStatistics(1L);
    }
}
