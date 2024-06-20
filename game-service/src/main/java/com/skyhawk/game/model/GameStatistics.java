package com.skyhawk.game.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Setter
@Getter
@Slf4j
@Table(name = "statistics")
public class GameStatistics {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "game_id", nullable = false)
        private Game game;

        @Column(nullable = false)
        private Long playerId;

        @Column(nullable = false)
        private Long teamId;

        @Column(nullable = false)
        private int points;

        @Column(nullable = false)
        private int rebounds;

        @Column(nullable = false)
        private int assists;

        @Column(nullable = false)
        private int steals;

        @Column(nullable = false)
        private int blocks;

        @Column(nullable = false)
        private int fouls;

        @Column(nullable = false)
        private int turnovers;

        @Column(nullable = false)
        private float minutesPlayed;

}
