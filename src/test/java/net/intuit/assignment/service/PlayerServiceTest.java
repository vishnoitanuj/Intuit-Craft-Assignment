package net.intuit.assignment.service;

import net.intuit.assignment.entity.GameEntity;
import net.intuit.assignment.entity.PlayerEntity;
import net.intuit.assignment.exception.GameException;
import net.intuit.assignment.repository.GameRepository;
import net.intuit.assignment.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private PlayerService target;

    @Test
    public void name() {
    }

    @Test
    public void createPlayerExists() throws GameException {
        PlayerEntity player = mock(PlayerEntity.class);
        when(player.getUid()).thenReturn("testUid");
        when(playerRepository.findPlayerEntityByUid(eq("testUid"))).thenReturn(player);
        assertEquals(target.create("testName", player), player);
    }

    @Test
    public void createNewExists() throws GameException {
        PlayerEntity player = mock(PlayerEntity.class);
        when(player.getName()).thenReturn("testName");
        when(playerRepository.save(any(PlayerEntity.class))).thenReturn(player);
        assertEquals(target.create("testName", player).getName(), "testName");
    }

    @Test(expected = GameException.class)
    public void createRaisesException() throws GameException {
        PlayerEntity player = mock(PlayerEntity.class);
        when(playerRepository.save(any(PlayerEntity.class))).thenThrow(RuntimeException.class);
        target.create("testName", player);
    }

    @Test
    public void getTopPlayer() throws GameException {
        GameEntity gameEntity = mock(GameEntity.class);
        PlayerEntity player = mock(PlayerEntity.class);
        when(player.getName()).thenReturn("testName");
        when(gameEntity.getId()).thenReturn(1L);
        when(gameEntity.getPlayer()).thenReturn(player);
        when(gameEntity.getScore()).thenReturn(1.0);
        when(gameRepository.findTopDistinctScoreOffset(4)).thenReturn(1);
        when(gameRepository.findTopPlayers(1)).thenReturn(Collections.singletonList(gameEntity));
        assertEquals(target.getTop5Player().size(), 1);
        assertEquals(target.getTopPlayer(5).size(), 1);
    }

    @Test(expected = GameException.class)
    public void getTop5PlayerRaisesException() throws GameException {
        when(gameRepository.findTopDistinctScoreOffset(4)).thenThrow(RuntimeException.class);
        target.getTop5Player();
    }

    @Test(expected = GameException.class)
    public void getTopPlayerRaisesException() throws GameException {
        when(gameRepository.findTopDistinctScoreOffset(4)).thenThrow(RuntimeException.class);
        target.getTopPlayer(5);
    }

    @Test(expected = GameException.class)
    public void getTopPlayerRaisesException2() throws GameException {
        when(gameRepository.findTopDistinctScoreOffset(4)).thenReturn(1);
        when(gameRepository.findTopPlayers(1)).thenThrow(RuntimeException.class);
        target.getTop5Player();
        target.getTopPlayer(5);
    }
}