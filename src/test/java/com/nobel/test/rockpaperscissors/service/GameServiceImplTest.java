package com.nobel.test.rockpaperscissors.service;

import static com.nobel.test.rockpaperscissors.model.Move.PAPER;
import static com.nobel.test.rockpaperscissors.model.Move.ROCK;
import static com.nobel.test.rockpaperscissors.model.Result.LOSE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nobel.test.rockpaperscissors.TestHelper;
import com.nobel.test.rockpaperscissors.config.GameConfig;
import com.nobel.test.rockpaperscissors.model.GameState;
import com.nobel.test.rockpaperscissors.response.GameOverResponse;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GameServiceImplTest {

    @Mock
    private GameState gameState;

    @Mock
    private PredictionService predictionService;

    @Mock
    private GameConfig gameConfig;

    @Mock
    private GameResponseService gameResponseService;

    @InjectMocks
    private GameServiceImpl gameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(gameConfig.getMaxRounds()).thenReturn(10);
    }

    @Test
    void play_ShouldReturnGameResponse_WhenUnderMaxRounds() {
        when(predictionService.predictNextMove()).thenReturn(PAPER);
        when(gameResponseService.createGameResponse(any(), any(), any(), anyInt()))
            .thenReturn(TestHelper.buildTestGameResponse());

        GameResponse response = (GameResponse) gameService.play(ROCK);

        assertNotNull(response);
        assertEquals(ROCK, response.getUserMove());
        assertEquals(PAPER, response.getComputerMove());
        assertEquals(LOSE, response.getResult());
    }

    @Test
    void play_ShouldReturnGameOverResponse_WhenMaxRoundsReached() {
        when(gameConfig.getMaxRounds()).thenReturn(0);
        when(gameResponseService.createGameOverResponse(anyInt(), anyInt(), anyInt(), anyString()))
            .thenReturn(new GameOverResponse(3, 2, 1, "game.is.over"));

        GameOverResponse response = (GameOverResponse) gameService.play(ROCK);

        assertNotNull(response);
        assertEquals(3, response.getWins());
        assertEquals(2, response.getLosses());
        assertEquals(1, response.getDraws());
    }

    @Test
    void getStatistics_ShouldReturnGameStatisticsResponse() {
        when(gameResponseService.createGameStatisticsResponse(anyInt(), anyInt(), anyInt()))
            .thenReturn(new GameStatisticsResponse(3, 2, 1));

        GameStatisticsResponse response = gameService.getStatistics();

        assertNotNull(response);
        assertEquals(3, response.getWins());
        assertEquals(2, response.getLosses());
        assertEquals(1, response.getDraws());
    }

    @Test
    void resetGame_ShouldResetState() {
        gameService.resetGame();
        verify(gameState).reset();
    }

}
