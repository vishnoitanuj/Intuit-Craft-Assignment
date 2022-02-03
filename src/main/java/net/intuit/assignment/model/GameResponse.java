package net.intuit.assignment.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameResponse {

    private Long gameId;
    private String playerName;
    private Double playerScore;
}
