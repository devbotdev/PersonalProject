package me.ib.PersonalProject.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.ib.PersonalProject.Main;
import me.ib.PersonalProject.util.State;
import me.ib.PersonalProject.util.Utility;

import java.io.IOException;
import java.util.Objects;

public final class MainController {

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

        launchButton.setOnAction(this::simulate);

        optionsButton.setOnAction(this::optionsMenu);

        exitButton.setOnAction(event -> System.exit(0));
    }

    private void optionsMenu(ActionEvent event) {
        try {
            Utility.state = State.TITLE_OPTIONS;

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib/TitleOptions.fxml")));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            stage.setTitle("Options");

            stage.getIcons().add(Utility.getResourceAsImage(Utility.optionsIcon));

            stage.setOnCloseRequest(event1 -> Utility.state = State.MAIN_MENU);

            stage.show();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private void simulate(ActionEvent event) {
        try {
            Utility.state = State.SIMULATION;

            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib/Simulator.fxml")));
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Simulator");

            stage.setFullScreen(true);

            stage.show();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
