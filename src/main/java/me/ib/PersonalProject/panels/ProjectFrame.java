package me.ib.PersonalProject.panels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.ib.PersonalProject.Main;
import me.ib.PersonalProject.util.State;
import me.ib.PersonalProject.util.Utility;

import java.util.Objects;

public final class ProjectFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Utility.state = State.MAIN_MENU;

        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib/titlescreen.fxml")));

        Scene scene = new Scene(root);

        primaryStage.setTitle("Personal Project");

        primaryStage.setMaximized(true);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
