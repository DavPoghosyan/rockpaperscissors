package com.nobel.test.rockpaperscissors.service;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import com.nobel.test.rockpaperscissors.response.GameOverResponse;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GameResponseServiceImpl implements GameResponseService {

    @Override
    public GameResponse createGameResponse(Move userMove, Move computerMove, Result result, int remainingRounds) {
        log.info("Creating GameResponse with remaining rounds: {}", remainingRounds);
        return GameResponse.builder()
            .userMove(userMove)
            .computerMove(computerMove)
            .result(result)
            .remainingRounds(remainingRounds)
            .build();
    }

    @Override
    public GameOverResponse createGameOverResponse(int wins, int losses, int draws, String messageCode) {
        log.info("Creating GameOverResponse with messageCode: {}", messageCode);
        return GameOverResponse.builder()
            .wins(wins)
            .losses(losses)
            .draws(draws)
            .messageCode(messageCode)
            .build();
    }

    @Override
    public GameStatisticsResponse createGameStatisticsResponse(int wins, int losses, int draws) {
        log.info("Creating GameStatisticsResponse");
        return GameStatisticsResponse.builder()
            .wins(wins)
            .losses(losses)
            .draws(draws)
            .build();
    }
}
