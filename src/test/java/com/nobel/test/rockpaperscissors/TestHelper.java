package com.nobel.test.rockpaperscissors;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import com.nobel.test.rockpaperscissors.response.GameResponse;

public abstract class TestHelper {

    public static GameResponse buildTestGameResponse() {
        return GameResponse.builder()
            .userMove(Move.ROCK)
            .computerMove(Move.PAPER)
            .result(Result.LOSE)
            .remainingRounds(9)
            .build();
    }
}
