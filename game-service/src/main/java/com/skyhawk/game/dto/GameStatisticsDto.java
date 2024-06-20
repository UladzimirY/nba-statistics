package com.skyhawk.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameStatisticsDto (
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id,
    Long gameId,
    Long playerId,
    Long teamId,
    int points,
    int rebounds,
    int assists,
    int steals,
    int blocks,
    int fouls,
    int turnovers,
    float minutesPlayed

)
{}
