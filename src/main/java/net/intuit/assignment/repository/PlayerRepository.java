package net.intuit.assignment.repository;

import net.intuit.assignment.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
    PlayerEntity findPlayerEntityById(Long id);
    PlayerEntity findPlayerEntityByUid(String uid);
}
