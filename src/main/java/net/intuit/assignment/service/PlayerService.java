package net.intuit.assignment.service;

import net.intuit.assignment.entity.GameEntity;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.repository.GameRepository;
import net.intuit.assignment.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public PlayerService(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    public PlayerEntity create(String name, PlayerEntity player) throws GameException {
        LOGGER.info("Saving info. Name = {}, Email = {}", name, player.getEmail());
        try {
            Optional<PlayerEntity> playerEntity = Optional.ofNullable(playerRepository.findPlayerEntityByUid(player.getUid()));
            if(playerEntity.isPresent())
                return playerEntity.get();
            player.setName(name);
            return playerRepository.save(player);
        }catch (Exception e){
            throw new GameException(HttpStatus.BAD_REQUEST, "Unable to save player data", e);
        }
    }

    public List<GameResponse> getTop5Player() throws GameException {
        try {
            int topNthScore = gameRepository.findTopDistinctScoreOffset(4);
            LOGGER.info("Top 5th Score found  = {}", topNthScore);
            return buildGamersList(topNthScore);
        } catch (Exception e){
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to get Top five games", e);
        }
    }

    public List<GameResponse> getTopPlayer(Integer n) throws GameException {
        try {
            int topNthScore = gameRepository.findTopDistinctScoreOffset(n-1);
            LOGGER.info("Top {}th Score found  = {}", n, topNthScore);
            return buildGamersList(topNthScore);
        } catch (Exception e){
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR,"Unable to get Top five games", e);
        }
    }

    private List<GameResponse> buildGamersList(int topNthScore) throws GameException {
        List<GameResponse> topGamers = new ArrayList<>();
        List<GameEntity> gameEntities;
        try {
            gameEntities = gameRepository.findTopPlayers(topNthScore);
        } catch (Exception e){
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured while retrieving top values", e);
        }
        for (GameEntity gameEntity : gameEntities) {
            GameResponse gameResponse = GameResponse.builder()
                    .gameId(gameEntity.getId())
                    .playerName(gameEntity.getPlayer().getName())
                    .playerScore(gameEntity.getScore())
                    .build();
            topGamers.add(gameResponse);
        }
        return topGamers;
    }
}
