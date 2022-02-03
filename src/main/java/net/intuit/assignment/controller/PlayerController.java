package net.intuit.assignment.controller;

import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
