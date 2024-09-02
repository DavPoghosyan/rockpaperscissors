package com.nobel.test.rockpaperscissors.utils;

import java.util.Random;

import com.nobel.test.rockpaperscissors.model.Move;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameUtils {

    private GameUtils() {
        log.error("Attempt to instantiate utility class");
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public static Move randomMove(Random random) {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }
}
