package net.intuit.assignment.controller;

import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createPlayer(@AuthenticationPrincipal PlayerEntity playerEntity, @RequestParam("name") String name) throws GameException {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.create(name, playerEntity));
    }

    @GetMapping("/get")
    public ResponseEntity<List<GameResponse>> getTopFive() throws GameException {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getTop5Player());
    }

    @PostMapping("/get")
    public ResponseEntity<List<GameResponse>> getTopPlayer(@RequestParam Integer n) throws GameException {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getTopPlayer(n));
    }
}
