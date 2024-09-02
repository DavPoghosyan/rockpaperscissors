package com.nobel.test.rockpaperscissors.service;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import com.nobel.test.rockpaperscissors.response.Response;

public interface GameService {
    Response play(Move userMove);

    GameStatisticsResponse getStatistics();

    void resetGame();
}
