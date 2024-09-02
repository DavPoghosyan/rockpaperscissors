package com.nobel.test.rockpaperscissors.service;

import java.util.concurrent.atomic.AtomicInteger;

import com.nobel.test.rockpaperscissors.config.GameConfig;
import com.nobel.test.rockpaperscissors.exception.InvalidMoveException;
import com.nobel.test.rockpaperscissors.model.GameState;
import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.model.Result;
import com.nobel.test.rockpaperscissors.response.GameOverResponse;
import com.nobel.test.rockpaperscissors.response.GameResponse;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import com.nobel.test.rockpaperscissors.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameServiceImpl implements GameService {
    private final GameState gameState;
    private final PredictionService predictionService;
    private final GameConfig gameConfig;
    private final GameResponseService gameResponseService;

    private final AtomicInteger currentRound = new AtomicInteger(0);

    @Override
    public Response play(Move userMove) {
        return (currentRound.get() >= gameConfig.getMaxRounds()) ? endGame() : continueGame(userMove);
    }

    private GameResponse continueGame(Move userMove) {
        log.info("Player move: {}", userMove);
        gameState.addUserMove(userMove);

        Move computerMove = predictionService.predictNextMove();
        gameState.addComputerMove(computerMove);

        Result result = determineResult(userMove, computerMove);
        log.info("Computer move: {}, Result: {}", computerMove, result);

        int remainingRounds = gameConfig.getMaxRounds() - currentRound.incrementAndGet();

        return gameResponseService.createGameResponse(userMove, computerMove, result, remainingRounds);
    }

    private GameOverResponse endGame() {
        log.info("Maximum number of rounds reached. Game Over.");
        return gameResponseService.createGameOverResponse(
            calculateResults(Result.WIN),
            calculateResults(Result.LOSE),
            calculateResults(Result.DRAW),
            "game.is.over"
        );
    }

    @Override
    public GameStatisticsResponse getStatistics() {
        log.info("Calculating match statistics.");
        return gameResponseService.createGameStatisticsResponse(
            calculateResults(Result.WIN),
            calculateResults(Result.LOSE),
            calculateResults(Result.DRAW)
        );
    }

    private int calculateResults(Result expectedResult) {
        return (int) gameState.getUserMoves().stream()
            .filter(move -> determineResult(move, predictionService.predictNextMove()) == expectedResult)
            .count();
    }

    @Override
    public void resetGame() {
        log.info("Resetting game. Clearing user and computer moves.");
        gameState.reset();
        currentRound.set(0);
    }

    private Result determineResult(Move userMove, Move computerMove) {
        if (userMove == computerMove) {
            log.debug("Round result: DRAW (User move: {}, Server move: {})", userMove, computerMove);
            return Result.DRAW;
        }
        Result result = calculateResult(userMove, computerMove);
        log.debug("Round result: {} (User move: {}, Server move: {})", result, userMove, computerMove);
        return result;
    }

    private Result calculateResult(Move userMove, Move computerMove) {
        log.debug("Calculating result: user move = {}, server move = {}", userMove, computerMove);
        switch (userMove) {
            case ROCK -> {
                return (computerMove == Move.SCISSORS) ? Result.WIN : Result.LOSE;
            }
            case PAPER -> {
                return (computerMove == Move.ROCK) ? Result.WIN : Result.LOSE;
            }
            case SCISSORS -> {
                return (computerMove == Move.PAPER) ? Result.WIN : Result.LOSE;
            }
            default -> throw new InvalidMoveException("Unexpected value: " + userMove);
        }
    }
}
