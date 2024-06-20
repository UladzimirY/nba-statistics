package com.skyhawk.team.service;

import com.skyhawk.team.dto.TeamDto;
import com.skyhawk.team.exception.TeamNotFoundException;
import com.skyhawk.team.mapper.TeamMapper;
import com.skyhawk.team.model.Team;
import com.skyhawk.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
    }

    public void createTeam(TeamDto teamDto) {
        final Team team = TeamMapper.INSTANCE.toEntity(teamDto);
        teamRepository.save(team);
    }

    public TeamDto getTeam(Long id) throws TeamNotFoundException {
        return teamRepository.findById(id)
                .map(TeamMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new TeamNotFoundException("Team not found with id: " + id));
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
