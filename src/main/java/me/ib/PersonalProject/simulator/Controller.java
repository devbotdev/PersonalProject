package me.ib.PersonalProject.simulator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
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

            hoveredPlanetName.setTranslateY(hoveredPlanetName.getLayoutBounds().getHeight());
            hoveredPlanetName.setVisible(true);
        });
    }

    private void updateOrbit(Arc orbit, Planet planet) {
        double radiusX = sun.getLayoutX() - planet.getSphere().getLayoutX() + planet.getSphere().getRadius();
        orbit.setRadiusX(Math.abs(radiusX));
    }

    private void createOrbit(Arc orbit, Planet planet) {
        double centerX = sun.getLayoutX();
        double centerY = sun.getLayoutY();
        double radiusX = planet.getSphere().getLayoutX() + planet.getSphere().getRadius();

        orbit.setCenterX(centerX);
        orbit.setCenterY(centerY);

        orbit.setRadiusX(radiusX);
        orbit.setRadiusY(100);
        orbit.setStartAngle(0);
        orbit.setLength(360);

        orbit.setLayoutX(centerX);
        orbit.setLayoutY(centerY);
        orbit.setFill(null);
        orbit.setStroke(Color.GRAY);
        orbit.setStrokeWidth(1);
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

        double newX;

        for (int i = 0; i < numberOfPlanets; i++) {
            if (planetsGroup.getChildren().get(i) == glowSphere) continue;
            if (i % 2 == 0) {
                newX = centerX - (((double) i / 2) * leftDistance) / 2 - offset;
            } else {
                newX = centerX + (((double) i / 2 + 1) * rightDistance) / 2 + offset;
            }

            planetsGroup.getChildren().get(i).setLayoutX(newX);
            planetsGroup.getChildren().get(i).setLayoutY(centerY);
        }

        updateOrbit(mercuryOrbit, mercury0);
        updateOrbit(venusOrbit, venus0);
        updateOrbit(earthOrbit, earth0);
        updateOrbit(marsOrbit, mars0);
        updateOrbit(ceresOrbit, ceres0);
        updateOrbit(jupiterOrbit, jupiter0);
        updateOrbit(saturnOrbit, saturn0);
        updateOrbit(uranusOrbit, uranus0);
        updateOrbit(neptuneOrbit, neptune0);
        updateOrbit(plutoOrbit, pluto0);
        updateOrbit(haumeaOrbit, haumea0);
        updateOrbit(makemakeOrbit, makemake0);
        updateOrbit(erisOrbit, eris0);
    }

    protected void shine(Sphere sphere) {
        if (sphere != sun) {
            glowSphere.setVisible(true);
            glowSphere.setLayoutX(sphere.getLayoutX());
            glowSphere.setLayoutY(sphere.getLayoutY());
            glowSphere.setRadius(sphere.getRadius() + 5);
        } else {
            sunShine.setVisible(true);
        }

        updateHoveredPlanetName(sphere.getId().substring(0, 1).toUpperCase() + sphere.getId().substring(1));
    }

    public void updateHoveredPlanetName(String planetName) {
        hoveredPlanetName.setText(planetName);
    }

    protected void unshine(Sphere sphere) {
        if (sphere == sun) {
            sunShine.setVisible(false);
        } else {
            glowSphere.setVisible(false);
        }

        updateHoveredPlanetName("Solar System");
    }

    private Planet sun0, mercury0, venus0, earth0, mars0, ceres0, jupiter0, saturn0, uranus0, neptune0, pluto0, haumea0, makemake0, eris0;
    private void initializePlanets() {
        sun0 = new Sun(sun);
        mercury0 = new Mercury(mercury);
        venus0 = new Venus(venus);
        earth0 = new Earth(earth);
        mars0 = new Mars(mars);
        ceres0 = new Ceres(ceres);
        jupiter0 = new Jupiter(jupiter);
        saturn0 = new Saturn(saturn);
        uranus0 = new Uranus(uranus);
        neptune0 = new Neptune(neptune);
        pluto0 = new Pluto(pluto);
        haumea0 = new Haumea(haumea);
        makemake0 = new Makemake(makemake);
        eris0 = new Eris(eris);

        glowMaterial = new PhongMaterial(Color.WHITE.deriveColor(1, 1, 1, 0.3));
        glowMaterial.setSpecularColor(Color.TRANSPARENT);

        glowSphere.setMaterial(glowMaterial);
        glowSphere.setVisible(false);

        sunShine.setMaterial(glowMaterial);
        sunShine.setVisible(false);

        createOrbit(mercuryOrbit, mercury0);
        createOrbit(venusOrbit, venus0);
        createOrbit(earthOrbit, earth0);
        createOrbit(marsOrbit, mars0);
        createOrbit(ceresOrbit, ceres0);
        createOrbit(jupiterOrbit, jupiter0);
        createOrbit(saturnOrbit, saturn0);
        createOrbit(uranusOrbit, uranus0);
        createOrbit(neptuneOrbit, neptune0);
        createOrbit(plutoOrbit, pluto0);
        createOrbit(haumeaOrbit, haumea0);
        createOrbit(makemakeOrbit, makemake0);
        createOrbit(erisOrbit, eris0);

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
    @FXML
    protected Text hoveredPlanetName;
    @FXML
    protected Arc mercuryOrbit;
    @FXML
    protected Arc venusOrbit;
    @FXML
    protected Arc earthOrbit;
    @FXML
    protected Arc marsOrbit;
    @FXML
    protected Arc ceresOrbit;
    @FXML
    protected Arc jupiterOrbit;
    @FXML
    protected Arc saturnOrbit;
    @FXML
    protected Arc uranusOrbit;
    @FXML
    protected Arc neptuneOrbit;
    @FXML
    protected Arc plutoOrbit;
    @FXML
    protected Arc haumeaOrbit;
    @FXML
    protected Arc makemakeOrbit;
    @FXML
    protected Arc erisOrbit;
}
