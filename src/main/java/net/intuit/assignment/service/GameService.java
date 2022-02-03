package net.intuit.assignment.service;

import net.intuit.assignment.entity.GameEntity;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.model.GameRequest;
import net.intuit.assignment.entity.User;
import net.intuit.assignment.repository.GameRepository;
import net.intuit.assignment.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Long submitScore(User user,GameRequest game){
        PlayerEntity player = playerRepository.findPlayerEntityByUserId(user.getUid());
        GameEntity gameEntity = GameEntity.builder()
                .score(game.getScore())
                .player(player)
                .build();
        return gameRepository.save(gameEntity).getId();
    }
}
