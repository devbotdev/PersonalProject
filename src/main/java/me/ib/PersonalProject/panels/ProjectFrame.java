package me.ib.PersonalProject.panels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.ib.PersonalProject.Main;
import me.ib.PersonalProject.util.State;
import me.ib.PersonalProject.util.Utility;

import java.util.Objects;

public final class ProjectFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Utility.state = State.MAIN_MENU;

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib/TitleScreen.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Personal Project");

        primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
        primaryStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
        primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());

        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");

        primaryStage.getIcons().add(Utility.getResourceAsImage(Utility.optionsIcon));

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
