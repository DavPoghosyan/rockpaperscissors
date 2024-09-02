package com.nobel.test.rockpaperscissors.model;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

@Component
public class GameState {
    private final List<Move> userMoves = new CopyOnWriteArrayList<>();
    private final List<Move> computerMoves = new CopyOnWriteArrayList<>();

    public void addUserMove(Move move) {
        userMoves.add(move);
    }

    public void addComputerMove(Move move) {
        computerMoves.add(move);
    }

    public List<Move> getUserMoves() {
        return Collections.unmodifiableList(userMoves);
    }

    public List<Move> getComputerMoves() {
        return Collections.unmodifiableList(computerMoves);
    }

    public void reset() {
        userMoves.clear();
        computerMoves.clear();
    }

    public long countUserMove(Move move) {
        return userMoves.stream().filter(m -> m == move).count();
    }

    public boolean isUserMovesEmpty() {
        return userMoves.isEmpty();
    }
}
