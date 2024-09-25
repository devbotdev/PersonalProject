package me.ib.panels;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.ib.Main;
import me.ib.control.Controller;
import me.ib.util.State;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

public final class ProjectFrame extends Application {
    private State state;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getClassLoader().getResource("me.ib.res/scene.fxml")));

        Scene scene = new Scene(root);

        state = State.MAIN_MENU;

        primaryStage.setTitle("Personal Project");

        primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
