package net.intuit.assignment.repository;

import net.intuit.assignment.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
    @Query(value = "SELECT * from GAME ORDER BY SCORE DESC LIMIT 5", nativeQuery = true)
    List<GameEntity> findTop5();
}
