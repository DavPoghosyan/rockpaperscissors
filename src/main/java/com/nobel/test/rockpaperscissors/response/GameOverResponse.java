package com.nobel.test.rockpaperscissors.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GameOverResponse extends GameStatisticsResponse {
    private String messageCode;

    public GameOverResponse(int wins, int losses, int draws, String messageCode) {
        super(wins, losses, draws);
        this.messageCode = messageCode;
    }
}
