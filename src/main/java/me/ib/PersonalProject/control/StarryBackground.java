package me.ib.PersonalProject.control;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.awt.*;
import java.util.Random;

public class StarryBackground {

    private final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private final int NUM_SMALL_STARS = 700;
    private final int NUM_MEDIUM_STARS = 200;
    private final int NUM_BIG_STARS = 100;
    private Circle[] smallStars, mediumStars, bigStars;

    private final Pane bgPane;

    public StarryBackground(Pane bgPane, int small, int med, int big, int speed) {
        this.bgPane = bgPane;

        RadialGradient gradient = new RadialGradient(
                0, 0, (double) WIDTH / 2, HEIGHT, HEIGHT,
                false, null,
                new Stop(0, javafx.scene.paint.Color.web("#1B2735")),
                new Stop(1, Color.web("#090A0F"))
        );
        bgPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(gradient, null, null)));

        createStars();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> animateStars()),
                new KeyFrame(Duration.millis(speed))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public StarryBackground(Pane bgPane) {
        this.bgPane = bgPane;

        RadialGradient gradient = new RadialGradient(
                0, 0, (double) WIDTH / 2, HEIGHT, HEIGHT,
                false, null,
                new Stop(0, javafx.scene.paint.Color.web("#1B2735")),
                new Stop(1, Color.web("#090A0F"))
        );
        bgPane.setBackground(new javafx.scene.layout.Background(
                new javafx.scene.layout.BackgroundFill(gradient, null, null)));

        createStars();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, event -> animateStars()),
                new KeyFrame(Duration.millis(16))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void createStars() {
        smallStars = new Circle[NUM_SMALL_STARS];
        mediumStars = new Circle[NUM_MEDIUM_STARS];
        bigStars = new Circle[NUM_BIG_STARS];

        Random random = new Random();
        for (int i = 0; i < NUM_SMALL_STARS; i++) {
            smallStars[i] = new Circle(1, Color.WHITE);
            smallStars[i].setLayoutX(random.nextInt(WIDTH));
            smallStars[i].setLayoutY(random.nextInt(HEIGHT));
            bgPane.getChildren().add(smallStars[i]);
        }

        for (int i = 0; i < NUM_MEDIUM_STARS; i++) {
            mediumStars[i] = new Circle(2, Color.WHITE);
            mediumStars[i].setLayoutX(random.nextInt(WIDTH));
            mediumStars[i].setLayoutY(random.nextInt(HEIGHT));
            bgPane.getChildren().add(mediumStars[i]);
        }

        for (int i = 0; i < NUM_BIG_STARS; i++) {
            bigStars[i] = new Circle(3, Color.WHITE);
            bigStars[i].setLayoutX(random.nextInt(WIDTH));
            bigStars[i].setLayoutY(random.nextInt(HEIGHT));
            bgPane.getChildren().add(bigStars[i]);
        }
    }

    private void animateStars() {
        Random random = new Random();
        for (Circle smallStar : smallStars) {
            double y = smallStar.getLayoutY() - 1;
            if (y < -1) {
                y = HEIGHT + 1;
                smallStar.setLayoutX(random.nextInt(WIDTH));
            }
            smallStar.setLayoutY(y);
        }

        for (Circle mediumStar : mediumStars) {
            double y = mediumStar.getLayoutY() - 0.5;
            if (y < -2) {
                y = HEIGHT + 2;
                mediumStar.setLayoutX(random.nextInt(WIDTH));
            }
            mediumStar.setLayoutY(y);
        }

        for (Circle bigStar : bigStars) {
            double y = bigStar.getLayoutY() - 0.25;
            if (y < -3) {
                y = HEIGHT + 3;
                bigStar.setLayoutX(random.nextInt(WIDTH));
            }
            bigStar.setLayoutY(y);
        }
    }
}
