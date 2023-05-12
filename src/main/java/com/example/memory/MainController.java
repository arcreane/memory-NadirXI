package com.example.memory;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

        @FXML
        private TextField player1NameField;

        @FXML
        private TextField player2NameField;

        @FXML
        private ComboBox <Theme> ThemeList;

        @FXML
        private ComboBox <Level> LevelList;

        @FXML
        private Button startButton;

        @FXML
        private void initialize() {
            ThemeList.getItems().addAll(Theme.values());
            LevelList.getItems().addAll(Level.values());
            startButton.setOnAction(event -> {
                System.out.println("Start button pressed");
                startGame();
            });
        }

        public void startGame() {
            String player1Name = player1NameField.getText();
            String player2Name = player2NameField.getText();
            Theme theme = ThemeList.getValue();
            Level level = LevelList.getValue();
            PlateauController plateauController = new PlateauController(player1Name, player2Name, level, theme);
            Stage stage = (Stage) startButton.getScene().getWindow();
            PlateauController.StartGame(stage);
        }
}