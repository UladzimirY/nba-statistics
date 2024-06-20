package com.skyhawk.game.mapper;

import com.skyhawk.game.dto.GameStatisticsDto;
import com.skyhawk.game.model.GameStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GameStatisticsMapper {
    GameStatisticsMapper INSTANCE = Mappers.getMapper(GameStatisticsMapper.class);
    @Mapping(source = "game.id", target = "gameId")
    GameStatisticsDto gameStatisticsToGameStatisticsDto(GameStatistics gameStatistics);
    @Mapping(source = "gameId", target = "game.id")
    GameStatistics gameStatisticsDtoToGameStatistics(GameStatisticsDto gameStatisticsDto);
}

