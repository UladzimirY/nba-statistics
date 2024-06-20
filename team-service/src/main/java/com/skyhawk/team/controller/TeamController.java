package com.skyhawk.team.controller;

import com.skyhawk.team.dto.TeamDto;
import com.skyhawk.team.exception.TeamNotFoundException;
import com.skyhawk.team.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity createTeam(@RequestBody @Valid TeamDto team) {
        teamService.createTeam(team);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDto> getTeam(@PathVariable Long id) throws TeamNotFoundException {
        return ResponseEntity.ok(teamService.getTeam(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDto> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok().build();
    }
}
