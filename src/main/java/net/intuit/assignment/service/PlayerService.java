package net.intuit.assignment.service;

import net.intuit.assignment.entity.GameEntity;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.repository.GameRepository;
import net.intuit.assignment.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public Long create(PlayerEntity player){
        return playerRepository.save(player).getId();
    }

    public List<GameResponse> getTop5Player(){
        List<GameEntity> gameEntities = gameRepository.findTop5();
        List<GameResponse> topGamers = new ArrayList<>();
        for(GameEntity gameEntity: gameEntities){
            GameResponse gameResponse = GameResponse.builder()
                    .playerName(gameEntity.getPlayer().getName())
                    .playerScore(gameEntity.getScore())
                    .build();
            topGamers.add(gameResponse);
        }
        return topGamers;
    }
}
