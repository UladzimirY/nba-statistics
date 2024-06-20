package com.skyhawk.statistics.dto;

public record PlayerStatDto (
        Long playerId,
        double averagePoints,
        double averageRebounds,
        double averageAssists
) {}
