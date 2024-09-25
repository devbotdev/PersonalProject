package me.ib.PersonalProject.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public final class Controller {

    @FXML public BorderPane mainPane;
    @FXML public Pane bgPane;
    @FXML public VBox vBox;
    @FXML public Label titleLabel;
    @FXML public Button launchButton, optionsButton, exitButton;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane);

        exitButton.setOnAction(event -> System.exit(0));
    }
}
