package com.skyhawk.game.mapper;


import com.skyhawk.game.dto.GameDto;
import com.skyhawk.game.model.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = GameStatisticsMapper.class)
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);
    GameDto gameToGameDto(Game game);
    Game gameDtoToGame(GameDto gameDto);
}

