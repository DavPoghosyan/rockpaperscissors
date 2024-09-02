package com.nobel.test.rockpaperscissors.service;

import java.util.Random;

import com.nobel.test.rockpaperscissors.model.GameState;
import com.nobel.test.rockpaperscissors.model.Move;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PredictionServiceImpl implements PredictionService {

    private final GameState gameState;
    private final Random random;

    @Override
    public Move predictNextMove() {
        log.info("Starting prediction for the next move.");
        if (gameState.isUserMovesEmpty()) {
            log.info("User move history is empty. Selecting a random move.");
            return getRandomMove();
        }
        return getStrategicMove();
    }

    private Move getRandomMove() {
        Move[] moves = Move.values();
        Move randomMove = moves[random.nextInt(moves.length)];
        log.info("Selected random move: {}", randomMove);
        return randomMove;
    }

    private Move getStrategicMove() {
        long rockCount = gameState.countUserMove(Move.ROCK);
        long paperCount = gameState.countUserMove(Move.PAPER);
        long scissorsCount = gameState.countUserMove(Move.SCISSORS);

        log.info("User move history - Rock: {}, Paper: {}, Scissors: {}", rockCount, paperCount, scissorsCount);

        if (rockCount > paperCount && rockCount > scissorsCount) {
            log.info("User favors ROCK. Predicting move: PAPER.");
            return Move.PAPER;
        } else if (paperCount > rockCount && paperCount > scissorsCount) {
            log.info("User favors PAPER. Predicting move: SCISSORS.");
            return Move.SCISSORS;
        } else if (scissorsCount > rockCount && scissorsCount > paperCount) {
            log.info("User favors SCISSORS. Predicting move: ROCK.");
            return Move.ROCK;
        } else {
            log.info("No clear user pattern detected. Selecting a random move.");
            return getRandomMove();
        }
    }
}
