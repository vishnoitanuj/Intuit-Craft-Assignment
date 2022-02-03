package net.intuit.assignment.controller;

import net.intuit.assignment.model.GameRequest;
import net.intuit.assignment.entity.User;
import net.intuit.assignment.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/game")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private GameService gameService;

    @PostMapping("/submit")
    public ResponseEntity<String> publishScore(@AuthenticationPrincipal User user, @RequestBody GameRequest game){
        LOGGER.info("");
        return ResponseEntity.status(HttpStatus.CREATED).body("Submitted Id = "+ gameService.submitScore(user, game));
    }
}
