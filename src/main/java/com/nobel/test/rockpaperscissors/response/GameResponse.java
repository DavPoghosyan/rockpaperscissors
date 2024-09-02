package com.nobel.test.rockpaperscissors.response;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class GameResponse extends Response {
    private Move userMove;
    private Move computerMove;
    private Result result;
    private int remainingRounds;
}
