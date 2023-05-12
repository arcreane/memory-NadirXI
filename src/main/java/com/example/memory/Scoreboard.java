package com.example.memory;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class Scoreboard {
    private final Players player1;
    private final Players player2;

    public Scoreboard(Players player1, Players player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Players getPlayer1() {
        return player1;
    }

    public Players getPlayer2() {
        return player2;
    }

    public void incrementPlayer1Score() {
        player1.incrementScore();
    }

    public void incrementPlayer2Score() {
        player2.incrementScore();
    }

    public void incrementScore() {
        if (player1.getScore() > player2.getScore()) {
            player1.incrementScore();
        } else if (player2.getScore() > player1.getScore()) {
            player2.incrementScore();
        }
    }

    public Node getScoreboard() {
        int totalScore = player1.getScore() + player2.getScore();
        Label label = new Label("Total Score: " + totalScore);
        return label;
    }

}
