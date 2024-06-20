package com.skyhawk.game.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@Slf4j
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long homeTeamId;

    @Column(nullable = false)
    private Long awayTeamId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp gameDate;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<GameStatistics> playerStatistics;
}
