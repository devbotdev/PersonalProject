package me.ib.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML public BorderPane mainPane;

    @FXML public VBox vBox;

    @FXML public Label titleLabel;

    @FXML public Button launchButton, optionsButton, exitButton;


    @FXML
    public void initialize() {
        exitButton.setOnAction(event -> System.exit(0));
    }
}
