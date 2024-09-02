package com.nobel.test.rockpaperscissors.controller;

import com.nobel.test.rockpaperscissors.model.Move;
import com.nobel.test.rockpaperscissors.response.GameStatisticsResponse;
import com.nobel.test.rockpaperscissors.response.Response;
import com.nobel.test.rockpaperscissors.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @PostMapping("/play")
    public Response play(@RequestParam Move move) {
        return gameService.play(move);
    }

    @GetMapping("/statistics")
    public GameStatisticsResponse getStatistics() {
        log.debug("Fetching current/last game results");
        return gameService.getStatistics();
    }

    @DeleteMapping("/reset")
    public void resetGame() {
        log.debug("Resetting game");
        gameService.resetGame();
    }
}
