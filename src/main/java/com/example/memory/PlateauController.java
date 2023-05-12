package com.example.memory;

import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlateauController {
    private static Scoreboard scoreboard;
    private static GridPane PlateauBoard;

    private Cards firstCard;
    private Cards secondCard;

    private final List <Cards> cards;

    private boolean isPlayerTurn;

    public PlateauController(String player1name, String player2name, Level level,Theme theme) {
        this.scoreboard = new Scoreboard(new Players(player1name), new Players(player2name));
        this.PlateauBoard = new GridPane();
        this.isPlayerTurn = true;
        this.cards = new ArrayList<>();
        createCards(level, theme);
        addCardsToBoard();
    }

    private void createCards(Level level, Theme theme) {
        int nbduos = level.getDuos();
        for (int i = 0; i < nbduos; i++) {
            try {
                Image frontImage = new Image(getClass().getResourceAsStream("/images/" + theme + "/" + i + ".png"));
                Image backImage = new Image(getClass().getResourceAsStream("/images/" + theme + "/back.png"));
                Cards card1 = new Cards(frontImage, backImage);
                Cards card2 = new Cards(frontImage, backImage);
                cards.add(card1);
                cards.add(card2);
            } catch (Exception e) {
                System.out.println("Error while loading images");
            }
        }
        Collections.shuffle(cards);
    }

    private void addCardsToBoard() {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < cards.size() / 4; j++) {
                PlateauBoard.add(cards.get(index).getView(), j, i);
                index++;
            }
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public GridPane getPlateauBoard() {
        return PlateauBoard;
    }

    public void flipCard(Cards card) {
        if (firstCard == null) {
            firstCard = card;
            firstCard.flip();
        } else if (secondCard == null) {
            secondCard = card;
            secondCard.flip();
            isPlayerTurn = false;
        }
    }

    public void checkCards() {
        if (firstCard != null && secondCard != null) {
            if (firstCard.getFrontImage() == secondCard.getFrontImage()) {
                firstCard.scale();
                secondCard.scale();
                scoreboard.incrementScore();
                if (isPlayerTurn) {
                    scoreboard.incrementPlayer1Score();
                } else {
                    scoreboard.incrementPlayer2Score();
                }
            } else {
                firstCard.reset();
                secondCard.reset();
                isPlayerTurn = !isPlayerTurn;
            }
            firstCard = null;
            secondCard = null;
        }
    }

    public boolean isGameOver() {
        for (Cards card : cards) {
            if (!card.isFlipped()) {
                return false;
            }
        }
        return true;
    }

    public void resetCards() {
        firstCard = null;
        secondCard = null;
        isPlayerTurn = true;
        for (Cards card : cards) {
            card.reset();
        }
    }

    public void resetBoard() {
        for (Cards card : cards) {
            card.reset();
        }
    }

    public static void StartGame(Stage stage) {
        VBox root = new VBox(); // the root pane of the scene
        root.getChildren().add(scoreboard.getScoreboard());
        root.getChildren().add(PlateauBoard);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
