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
            Scene scene = new Scene(root);

            newStage.initOwner(((Node) event.getSource()).getScene().getWindow());
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.setScene(scene);

            newStage.setTitle("Options");

            newStage.getIcons().add(Utility.getResourceAsImage(Utility.optionsIcon));

            newStage.show();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }
}
