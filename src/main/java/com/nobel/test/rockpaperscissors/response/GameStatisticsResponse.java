package com.nobel.test.rockpaperscissors.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GameStatisticsResponse extends Response {
    private int wins;
    private int losses;
    private int draws;

    public GameStatisticsResponse(int wins, int losses, int draws) {
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }
}
