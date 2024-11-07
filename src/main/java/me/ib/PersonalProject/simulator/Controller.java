package me.ib.PersonalProject.simulator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import me.ib.PersonalProject.control.StarryBackground;
import me.ib.PersonalProject.simulator.bodies.*;
import me.ib.PersonalProject.simulator.bodies.dwarf.*;

public class Controller {

    public final PhongMaterial material = new PhongMaterial();
    private PhongMaterial glowMaterial;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 400, 150, 50, 95);

        initializePlanets();

        Platform.runLater(() -> {
            Stage stage = (Stage) bgPane.getScene().getWindow();
            stage.widthProperty().addListener((obs, oldVal, newVal) -> resizePlanets(stage));
            stage.heightProperty().addListener((obs, oldVal, newVal) -> resizePlanets(stage));
            resizePlanets(stage);
        });
    }

    public void setActions(Sphere planet) {
        planet.setOnMouseEntered(event -> shine(planet));
        planet.setOnMouseExited(event -> unshine(planet));
    }

    private void resizePlanets(Stage stage) {
        double width = stage.getWidth();
        double height = stage.getHeight();

        double centerX = width / 2;
        double centerY = height / 2;

        sun.setLayoutX(centerX);
        sun.setLayoutY(centerY);

        int numberOfPlanets = planetsGroup.getChildren().size();

        double totalWidth = width - 140;
        double offset = 50;

        double rightDistance = totalWidth / (((double) numberOfPlanets / 2) + 1);
        double leftDistance = totalWidth / (((double) numberOfPlanets / 2));

        Sphere planet;

        double newX;

        for (int i = 0; i < numberOfPlanets; i++) {
            planet = (Sphere) planetsGroup.getChildren().get(i);
            if (planet == glowSphere) continue;
            if (i % 2 == 0) {
                newX = centerX - (((double) i / 2) * leftDistance) / 2 - offset;
            } else {
                newX = centerX + (((double) i / 2 + 1) * rightDistance) / 2 + offset;
            }
            planet.setLayoutX(newX);
            planet.setLayoutY(centerY);
        }
    }

    protected void shine(Sphere sphere) {
        if (sphere == sun) {
            sunShine.setVisible(true);
            return;
        }

        glowSphere.setVisible(true);
        glowSphere.setLayoutX(sphere.getLayoutX());
        glowSphere.setLayoutY(sphere.getLayoutY());
        glowSphere.setRadius(sphere.getRadius() + 5);
    }

    protected void unshine(Sphere sphere) {
        if (sphere == sun) {
            sunShine.setVisible(false);
            return;
        }

        glowSphere.setVisible(false);
    }

    private void initializePlanets() {
        Planet sunO = new Sun(sun);
        Planet mercury0 = new Mercury(mercury);
        Planet venus0 = new Venus(venus);
        Planet earth0 = new Earth(earth);
        Planet mars0 = new Mars(mars);
        Planet ceres0 = new Ceres(ceres);
        Planet jupiter0 = new Jupiter(jupiter);
        Planet saturn0 = new Saturn(saturn);
        Planet uranus0 = new Uranus(uranus);
        Planet neptune0 = new Neptune(neptune);
        Planet pluto0 = new Pluto(pluto);
        Planet haumea0 = new Haumea(haumea);
        Planet makemake0 = new Makemake(makemake);
        Planet eris0 = new Eris(eris);

        glowMaterial = new PhongMaterial(Color.WHITE.deriveColor(1, 1, 1, 0.3));
        glowMaterial.setSpecularColor(Color.TRANSPARENT);

        glowSphere.setMaterial(glowMaterial);
        glowSphere.setVisible(false);

        sunShine.setMaterial(glowMaterial);
        sunShine.setVisible(false);

        setActions(sun);
        setActions(mercury);
        setActions(venus);
        setActions(earth);
        setActions(mars);
        setActions(ceres);
        setActions(jupiter);
        setActions(saturn);
        setActions(uranus);
        setActions(neptune);
        setActions(pluto);
        setActions(eris);
        setActions(haumea);
        setActions(makemake);
    }

    @FXML
    protected Sphere glowSphere;
    @FXML
    protected Sphere sunShine;
    @FXML
    public Pane bgPane;
    @FXML
    public Group planetsGroup;
    @FXML
    protected Sphere sun;
    @FXML
    protected Sphere mercury;
    @FXML
    protected Sphere venus;
    @FXML
    protected Sphere earth;
    @FXML
    protected Sphere mars;
    @FXML
    protected Sphere jupiter;
    @FXML
    protected Sphere ceres;
    @FXML
    protected Sphere saturn;
    @FXML
    protected Sphere uranus;
    @FXML
    protected Sphere neptune;
    @FXML
    protected Sphere pluto;
    @FXML
    protected Sphere haumea;
    @FXML
    protected Sphere makemake;
    @FXML
    protected Sphere eris;
}
