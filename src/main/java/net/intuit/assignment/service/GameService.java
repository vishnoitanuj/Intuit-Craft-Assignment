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

@Service
public class GameService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameResponse submitScore(PlayerEntity player, Double score) throws GameException {
        PlayerEntity playerEntity;
        try {
            playerEntity = playerRepository.findPlayerEntityByUid(player.getUid());
        }catch (Exception e){
            throw new GameException(HttpStatus.UNPROCESSABLE_ENTITY, "Unrecognised User", e);
        }
        LOGGER.info("Submitting score for player = {}, Score = {}", player.getName(), score);
        GameEntity gameEntity = GameEntity.builder()
                .score(score)
                .player(playerEntity)
                .build();
        try {
            GameEntity submittedGame = gameRepository.save(gameEntity);
            return GameResponse.builder()
                    .playerName(playerEntity.getName())
                    .playerScore(score)
                    .gameId(submittedGame.getId())
                    .build();
        } catch (Exception e){
            throw new GameException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not save player scores", e);
        }
    }
}
