package net.intuit.assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "GAME", indexes = {
        @Index(
                name = "game_index",
                columnList = "score DESC",
                unique = false
        )
})
public class GameEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Player_Id", referencedColumnName = "ID")
    private PlayerEntity player;

    @Column(name = "SCORE")
    private Double score;
}
