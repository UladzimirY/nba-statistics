package com.skyhawk.statistics.controller;

import com.skyhawk.statistics.dto.PlayerStatDto;
import com.skyhawk.statistics.dto.TeamStatDto;
import com.skyhawk.statistics.exception.NotPlayerStatisticsAvailable;
import com.skyhawk.statistics.exception.NotTeamStatisticsAvailable;
import com.skyhawk.statistics.service.StatisticsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;



    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/players/{id}/season-average")
    public ResponseEntity<PlayerStatDto> getPlayerSeasonAverage(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.getPlayerSeasonAverage(id));
    }

    @GetMapping("/teams/{id}/season-average")
    public ResponseEntity<TeamStatDto> getTeamSeasonAverage(@PathVariable Long id) {
        return ResponseEntity.ok(statisticsService.getTeamSeasonAverage(id));
    }

}
