package com.nobel.test.rockpaperscissors.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import com.nobel.test.rockpaperscissors.response.GameOverResponse;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameResponseServiceImplTest {

    private GameResponseService gameResponseService;

    @BeforeEach
    void setUp() {
        gameResponseService = new GameResponseServiceImpl();
    }

    @Test
    void createGameResponse_ShouldReturnValidResponse() {
        GameResponse response = gameResponseService.createGameResponse(Move.ROCK, Move.PAPER, Result.LOSE, 3);
        assertEquals(Move.ROCK, response.getUserMove());
        assertEquals(Move.PAPER, response.getComputerMove());
        assertEquals(Result.LOSE, response.getResult());
        assertEquals(3, response.getRemainingRounds());
    }

    @Test
    void createGameOverResponse_ShouldReturnValidResponse() {
        GameOverResponse response = gameResponseService.createGameOverResponse(2, 1, 1, "game.is.over");
        assertEquals(2, response.getWins());
        assertEquals(1, response.getLosses());
        assertEquals(1, response.getDraws());
        assertEquals("game.is.over", response.getMessageCode());
    }

    @Test
    void createGameStatisticsResponse_ShouldReturnValidResponse() {
        GameStatisticsResponse response = gameResponseService.createGameStatisticsResponse(2, 1, 1);
        assertEquals(2, response.getWins());
        assertEquals(1, response.getLosses());
        assertEquals(1, response.getDraws());
    }
}
