package me.ib.control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import me.ib.util.State;
import me.ib.util.Utility;

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
