package net.intuit.assignment.service;

import net.intuit.assignment.entity.GameEntity;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.model.GameResponse;
import net.intuit.assignment.repository.GameRepository;
import net.intuit.assignment.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private GameService target;

    @Test
    public void submitScore() throws GameException {
        PlayerEntity playerEntity = mock(PlayerEntity.class);
        when(playerEntity.getUid()).thenReturn("testId");
        when(playerEntity.getName()).thenReturn("testName");
        when(playerRepository.findPlayerEntityByUid(eq("testId"))).thenReturn(playerEntity);
        GameEntity gameEntity = mock(GameEntity.class);
        when(gameEntity.getId()).thenReturn(1L);
        when(gameRepository.save(any(GameEntity.class))).thenReturn(gameEntity);
        assertEquals(target.submitScore(playerEntity, 10.0).getPlayerName(), "testName");
    }

    @Test(expected = GameException.class)
    public void submitScoreRaisesException() throws GameException {
        PlayerEntity playerEntity = mock(PlayerEntity.class);
        when(playerEntity.getUid()).thenReturn("testId");
        when(playerRepository.findPlayerEntityByUid(eq("testId"))).thenThrow(RuntimeException.class);
        target.submitScore(playerEntity, 10.0);
    }

    @Test(expected = GameException.class)
    public void submitScoreRaisesException2() throws GameException {
        PlayerEntity playerEntity = mock(PlayerEntity.class);
        when(playerEntity.getUid()).thenReturn("testId");
        when(playerEntity.getName()).thenReturn("testName");
        when(playerRepository.findPlayerEntityByUid(eq("testId"))).thenReturn(playerEntity);
        when(gameRepository.save(any(GameEntity.class))).thenThrow(RuntimeException.class);
        target.submitScore(playerEntity, 10.0);
    }
}