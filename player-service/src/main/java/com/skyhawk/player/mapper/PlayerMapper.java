package com.skyhawk.player.mapper;

import com.skyhawk.player.dto.PlayerDto;
import com.skyhawk.player.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);
    PlayerDto toDto(Player player);
    Player toEntity(PlayerDto dto);
}
