package me.ib.panels;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.ib.Main;
import me.ib.util.State;
import me.ib.util.Utility;

import java.util.Objects;

public final class ProjectFrame extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib.res/scene.fxml")));

        Scene scene = new Scene(root);

        Utility.state = State.MAIN_MENU;

        primaryStage.setTitle("Personal Project");

        primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
