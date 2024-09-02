package com.nobel.test.rockpaperscissors.service;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import com.nobel.test.rockpaperscissors.response.GameOverResponse;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;

public interface GameResponseService {
    GameResponse createGameResponse(Move userMove, Move computerMove, Result result, int remainingRounds);

    GameOverResponse createGameOverResponse(int wins, int losses, int draws, String messageCode);

    GameStatisticsResponse createGameStatisticsResponse(int wins, int losses, int draws);
}
