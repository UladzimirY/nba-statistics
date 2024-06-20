package com.skyhawk.statistics.client;

import com.skyhawk.statistics.exception.ClientErrorException;
import com.skyhawk.statistics.exception.ServerErrorException;
import com.skyhawk.statistics.model.GameStat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GameClient {
    private final RestTemplate restTemplate;
    private final String gameServiceBaseUrl;
    private final String playerStatisticsUrl;
    private final String teamStatisticsUrl;

    public GameClient(RestTemplate restTemplate,
                      @Value("${game.service.base.url}") String gameServiceBaseUrl,
                      @Value("${game.service.player.statistics.url}") String playerStatisticsUrl,
                      @Value("${game.service.team.statistics.url}") String teamStatisticsUrl) {
        this.restTemplate = restTemplate;
        this.gameServiceBaseUrl = gameServiceBaseUrl;
        this.playerStatisticsUrl = playerStatisticsUrl;
        this.teamStatisticsUrl = teamStatisticsUrl;
    }

    public List<GameStat> getPlayerStatistics(Long playerId) {
        try {
            ResponseEntity<List<GameStat>> response = restTemplate.exchange(
                    gameServiceBaseUrl + playerStatisticsUrl + playerId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new ClientErrorException("Client error while fetching player statistics: " + e.getMessage(), e);
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException("Server error while fetching player statistics: " + e.getMessage(), e);
        }
    }

    public List<GameStat> getTeamStatistics(Long teamId) {
        try {
            ResponseEntity<List<GameStat>> response = restTemplate.exchange(
                    gameServiceBaseUrl + teamStatisticsUrl + teamId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new ClientErrorException("Client error while fetching team statistics: " + e.getMessage(), e);
        } catch (HttpServerErrorException e) {
            throw new ServerErrorException("Server error while fetching team statistics: " + e.getMessage(), e);
        }
    }
}

