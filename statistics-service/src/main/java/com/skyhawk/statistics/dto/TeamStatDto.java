package com.skyhawk.statistics.dto;

public record TeamStatDto(
        Long teamId,
        double averagePoints,
        double averageRebounds,
        double averageAssists
) { }
