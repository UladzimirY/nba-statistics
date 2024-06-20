package com.skyhawk.player.service;

import com.skyhawk.player.dto.PlayerDto;
import com.skyhawk.player.exception.PlayerNotFoundException;
import com.skyhawk.player.mapper.PlayerMapper;
import com.skyhawk.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
    }

    public void createPlayer(PlayerDto playerDto) {
        final var player = PlayerMapper.INSTANCE.toEntity(playerDto);
        playerRepository.save(player);
    }

    public PlayerDto getPlayer(Long id) throws PlayerNotFoundException {
        return playerRepository.findById(id)
                .map(PlayerMapper.INSTANCE::toDto)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + id));
    }

    public void deletePlayer(Long id) {
            playerRepository.deleteById(id);
    }

}
