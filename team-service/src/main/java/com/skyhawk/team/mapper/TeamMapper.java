package com.skyhawk.team.mapper;

import com.skyhawk.team.model.Team;
import com.skyhawk.team.dto.TeamDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);
    TeamDto toDTO(Team team);
    Team toEntity(TeamDto teamDTO);
}
