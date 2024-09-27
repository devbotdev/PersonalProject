package me.ib.PersonalProject.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.ib.PersonalProject.Main;
import me.ib.PersonalProject.util.State;
import me.ib.PersonalProject.util.Utility;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public final class MainMenuController {

    @FXML
    public BorderPane mainPane;
    @FXML
    public Pane bgPane;
    @FXML
    public VBox vBox;
    @FXML
    public Label titleLabel;
    @FXML
    public Button launchButton, optionsButton, exitButton;
    @FXML
    public StackPane stackPane;


    @FXML
    public void initialize() {
        new StarryBackground(bgPane);

        optionsButton.setOnAction(this::optionsMenu);

        exitButton.setOnAction(event -> System.exit(0));
    }

    private void optionsMenu(ActionEvent event) {
        try {
            Utility.state = State.TITLE_OPTIONS;

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib/titleoptions.fxml")));
            Stage newStage = new Stage();

            BorderPane titleBar = new BorderPane();
            titleBar.setPrefHeight(30);
            titleBar.setStyle("-fx-background-color: #333;");

            Label titleLabel = new Label("Options");
            titleLabel.setStyle("-fx-text-fill: #fff;");
            titleLabel.setAlignment(Pos.CENTER);

            titleBar.setCenter(titleLabel);

            BorderPane newRoot = new BorderPane();
            newRoot.setTop(titleBar);
            newRoot.setCenter(root);

            Scene scene = new Scene(newRoot);

            Button closeButton = new Button("X");
            closeButton.setOnAction(event2 -> newStage.close());
            titleBar.setRight(closeButton);

            AtomicReference<Double> xOffset = new AtomicReference<>((double) 0);
            AtomicReference<Double> yOffset = new AtomicReference<>((double) 0);

            titleBar.setOnMousePressed(event3 -> {
                xOffset.set(event3.getSceneX());
                yOffset.set(event3.getSceneY());
            });

            titleBar.setOnMouseDragged(event3 -> {
                newStage.setX(event3.getScreenX() - xOffset.get());
                newStage.setY(event3.getScreenY() - yOffset.get());
            });

            newStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.initStyle(StageStyle.UNDECORATED);

            newStage.setScene(scene);

            newStage.setOnCloseRequest(event1 -> Utility.state = State.MAIN_MENU);

            newStage.show();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
