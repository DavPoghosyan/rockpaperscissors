package com.nobel.test.rockpaperscissors.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nobel.test.rockpaperscissors.TestHelper;
import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import com.nobel.test.rockpaperscissors.response.Response;
import com.nobel.test.rockpaperscissors.service.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    @Mock
    private GameService gameService;

    @InjectMocks
    private GameController gameController;

    @Test
    void play_ShouldReturnGameResponse() {
        when(gameService.play(any())).thenReturn(TestHelper.buildTestGameResponse());

        Response response = gameController.play(Move.ROCK);

        GameResponse expectedResponse = TestHelper.buildTestGameResponse();
        assertTrue(response instanceof GameResponse);
        assertEquals(expectedResponse, response);

        verify(gameService).play(any());
    }

    @Test
    void getStatistics_ShouldReturnGameStatisticsResponse() {
        var expectedResponse = new GameStatisticsResponse(3, 2, 1);
        when(gameService.getStatistics()).thenReturn(expectedResponse);

        var response = gameController.getStatistics();
        assertEquals(expectedResponse.getWins(), response.getWins());
        assertEquals(expectedResponse.getLosses(), response.getLosses());
        assertEquals(expectedResponse.getDraws(), response.getDraws());

        verify(gameService).getStatistics();
    }

    @Test
    void resetGame_ShouldReturnSuccessMessage() {
        gameController.resetGame();
        verify(gameService).resetGame();
    }
}
