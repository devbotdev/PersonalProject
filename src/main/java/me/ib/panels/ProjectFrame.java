package me.ib.panels;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.ib.Main;
import me.ib.control.Controller;
import me.ib.util.State;

import java.awt.*;
import java.util.Objects;

public class ProjectFrame extends Application {
    private State state;

    public ProjectFrame() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib.res/scene.fxml")));

        primaryStage.setTitle("Personal Project");

        Scene scene = new Scene(root);

        state = State.MAIN_MENU;

        primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
