package net.intuit.assignment.controller;

import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.entity.User;
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
    public ResponseEntity<String> createPlayer(@AuthenticationPrincipal User user, @RequestBody PlayerEntity player){
        player.setUserId(user.getUid());
        return ResponseEntity.status(HttpStatus.OK).body("Created Id = " + playerService.create(player));
    }

    @GetMapping("/get")
    public ResponseEntity<List<GameResponse>> get(){
        return ResponseEntity.status(HttpStatus.OK).body(playerService.getTop5Player());
    }
}
