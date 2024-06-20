package com.skyhawk.game.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.List;

public record GameDto (
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        Long homeTeamId,
        Long awayTeamId,
        Timestamp gameDate,
        List<GameStatisticsDto> playerStatistics
) {
}
