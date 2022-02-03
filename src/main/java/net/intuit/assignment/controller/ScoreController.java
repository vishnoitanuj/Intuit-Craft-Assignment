package net.intuit.assignment.controller;

import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {
    private final PlayerService playerService;

    public ScoreController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/getTopFive")
    public ResponseEntity<List<GameResponse>> getTopFive() throws GameException {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getTop5Player());
    }

    @GetMapping("/getTop")
    public ResponseEntity<List<GameResponse>> getTopPlayer(@RequestParam Integer n) throws GameException {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getTopPlayer(n));
    }
}
