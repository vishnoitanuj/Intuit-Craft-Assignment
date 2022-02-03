package net.intuit.assignment.repository;

import net.intuit.assignment.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {

    @Query(value = "SELECT DISTINCT SCORE from GAME ORDER BY SCORE DESC LIMIT 1 OFFSET :n", nativeQuery = true)
    Integer findTopDistinctScoreOffset(Integer n);

    @Query(value = "SELECT * from GAME WHERE SCORE >= :s ORDER BY SCORE DESC", nativeQuery = true)
    List<GameEntity> findTopPlayers(Integer s);
}
