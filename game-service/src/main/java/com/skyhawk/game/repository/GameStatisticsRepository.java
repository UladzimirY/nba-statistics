package com.skyhawk.game.repository;

import com.skyhawk.game.model.Game;
import com.skyhawk.game.model.GameStatistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameStatisticsRepository extends CrudRepository<Game, Long> {
    List<GameStatistics> findByPlayerId(Long playerId);
    List<GameStatistics> findByTeamId(Long teamId);
}
