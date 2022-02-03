package net.intuit.assignment.controller;

import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/submit")
    public ResponseEntity<GameResponse> publishScore(@AuthenticationPrincipal PlayerEntity player, @RequestParam Double score) throws GameException {
        LOGGER.info("Submitting for score ={} for player = {}", score, player.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.submitScore(player, score));
    }
}
