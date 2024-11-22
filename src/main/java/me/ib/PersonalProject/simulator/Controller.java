package me.ib.PersonalProject.simulator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.ib.PersonalProject.control.StarryBackground;
import me.ib.PersonalProject.simulator.bodies.*;
import me.ib.PersonalProject.simulator.bodies.dwarf.*;

public class Controller {

    public final PhongMaterial material = new PhongMaterial();
    private PhongMaterial glowMaterial;
    private Stage stage;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 400, 150, 50, 95);

        initializePlanets();

        Platform.runLater(() -> {
            stage = (Stage) bgPane.getScene().getWindow();
            stage.widthProperty().addListener((obs, oldVal, newVal) -> resizePlanets(stage));
            stage.heightProperty().addListener((obs, oldVal, newVal) -> resizePlanets(stage));
            resizePlanets(stage);

            stage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) fullscreenButton.setVisible(true);
            });

            fullscreenButton.setVisible(false);

            hoveredPlanetName.setTranslateY(hoveredPlanetName.getLayoutBounds().getHeight());
            hoveredPlanetName.setVisible(true);
        });
    }

    private void updateOrbit(Arc topOrbit, Arc bottomOrbit, Planet planet) {
        double radiusX = Math.abs(sun.getLayoutX() - planet.getSphere().getLayoutX() + planet.getSphere().getRadius());
        topOrbit.setRadiusX(radiusX);
        topOrbit.setRadiusY(40 + radiusX / 8);
        topOrbit.setTranslateY((sun.getRadius() / - 1.45) + (8200 / topOrbit.getRadiusX()));

        bottomOrbit.setRadiusX(radiusX);
        bottomOrbit.setRadiusY(40 + radiusX / 8);
        bottomOrbit.setTranslateY((sun.getRadius() / 1.45) - (8200 / topOrbit.getRadiusX()));

        topOrbit.setVisible(true);
        bottomOrbit.setVisible(true);
    }

    private void createOrbit(Arc topOrbit, Arc bottomOrbit, Planet planet) {
        double radiusX = planet.getSphere().getLayoutX() + planet.getSphere().getRadius();

        topOrbit.setVisible(false);
        bottomOrbit.setVisible(false);

        topOrbit.setRadiusX(radiusX);
        topOrbit.setRadiusY(radiusX / 2);
        topOrbit.setStartAngle(0);
        topOrbit.setLength(180);

        bottomOrbit.setRadiusX(radiusX);
        bottomOrbit.setRadiusY(radiusX / 2);
        bottomOrbit.setStartAngle(180);
        bottomOrbit.setLength(180);

        topOrbit.setFill(null);
        topOrbit.setStroke(Color.GRAY);
        topOrbit.setStrokeWidth(1);

        bottomOrbit.setFill(null);
        bottomOrbit.setStroke(Color.GRAY);
        bottomOrbit.setStrokeWidth(1);

        topOrbit.setType(ArcType.OPEN);
        bottomOrbit.setType(ArcType.OPEN);
    }

    private void toggleOrbit(ActionEvent e) {
        if (orbitCheckBox.isSelected()) {
            mercuryOrbit.setVisible(true);
            venusOrbit.setVisible(true);
            earthOrbit.setVisible(true);
            marsOrbit.setVisible(true);
            ceresOrbit.setVisible(true);
            jupiterOrbit.setVisible(true);
            saturnOrbit.setVisible(true);
            uranusOrbit.setVisible(true);
            neptuneOrbit.setVisible(true);
            plutoOrbit.setVisible(true);
            haumeaOrbit.setVisible(true);
            makemakeOrbit.setVisible(true);
            erisOrbit.setVisible(true);
            mercuryOrbit1.setVisible(true);
            venusOrbit1.setVisible(true);
            earthOrbit1.setVisible(true);
            marsOrbit1.setVisible(true);
            ceresOrbit1.setVisible(true);
            jupiterOrbit1.setVisible(true);
            saturnOrbit1.setVisible(true);
            uranusOrbit1.setVisible(true);
            neptuneOrbit1.setVisible(true);
            plutoOrbit1.setVisible(true);
            haumeaOrbit1.setVisible(true);
            makemakeOrbit1.setVisible(true);
            erisOrbit1.setVisible(true);
        } else {
            mercuryOrbit.setVisible(false);
            venusOrbit.setVisible(false);
            earthOrbit.setVisible(false);
            marsOrbit.setVisible(false);
            ceresOrbit.setVisible(false);
            jupiterOrbit.setVisible(false);
            saturnOrbit.setVisible(false);
            uranusOrbit.setVisible(false);
            neptuneOrbit.setVisible(false);
            plutoOrbit.setVisible(false);
            haumeaOrbit.setVisible(false);
            makemakeOrbit.setVisible(false);
            erisOrbit.setVisible(false);
            mercuryOrbit1.setVisible(false);
            venusOrbit1.setVisible(false);
            earthOrbit1.setVisible(false);
            marsOrbit1.setVisible(false);
            ceresOrbit1.setVisible(false);
            jupiterOrbit1.setVisible(false);
            saturnOrbit1.setVisible(false);
            uranusOrbit1.setVisible(false);
            neptuneOrbit1.setVisible(false);
            plutoOrbit1.setVisible(false);
            haumeaOrbit1.setVisible(false);
            makemakeOrbit1.setVisible(false);
            erisOrbit1.setVisible(false);
        }
    }
    public void setActions(Sphere planet) {
        planet.setOnMouseEntered(event -> shine(planet));
        planet.setOnMouseExited(event -> unshine(planet));

        orbitCheckBox.setOnAction(this::toggleOrbit);
        fullscreenButton.setOnAction(event -> setFullscreen());
    }

    public void setFullscreen() {
        stage.setFullScreen(true);
        fullscreenButton.setVisible(false);
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

        updateOrbit(mercuryOrbit, mercuryOrbit1, mercury0);
        updateOrbit(venusOrbit, venusOrbit1, venus0);
        updateOrbit(earthOrbit, earthOrbit1, earth0);
        updateOrbit(marsOrbit, marsOrbit1, mars0);
        updateOrbit(ceresOrbit, ceresOrbit1, ceres0);
        updateOrbit(jupiterOrbit, jupiterOrbit1, jupiter0);
        updateOrbit(saturnOrbit, saturnOrbit1, saturn0);
        updateOrbit(uranusOrbit, uranusOrbit1, uranus0);
        updateOrbit(neptuneOrbit, neptuneOrbit1, neptune0);
        updateOrbit(plutoOrbit, plutoOrbit1, pluto0);
        updateOrbit(haumeaOrbit, haumeaOrbit1, haumea0);
        updateOrbit(makemakeOrbit, makemakeOrbit1, makemake0);
        updateOrbit(erisOrbit, erisOrbit1, eris0);
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

        createOrbit(mercuryOrbit, mercuryOrbit1, mercury0);
        createOrbit(venusOrbit, venusOrbit1, venus0);
        createOrbit(earthOrbit, earthOrbit1, earth0);
        createOrbit(marsOrbit, marsOrbit1, mars0);
        createOrbit(ceresOrbit, ceresOrbit1, ceres0);
        createOrbit(jupiterOrbit, jupiterOrbit1, jupiter0);
        createOrbit(saturnOrbit, saturnOrbit1, saturn0);
        createOrbit(uranusOrbit, uranusOrbit1, uranus0);
        createOrbit(neptuneOrbit, neptuneOrbit1, neptune0);
        createOrbit(plutoOrbit, plutoOrbit1, pluto0);
        createOrbit(haumeaOrbit, haumeaOrbit1, haumea0);
        createOrbit(makemakeOrbit, makemakeOrbit1, makemake0);
        createOrbit(erisOrbit, erisOrbit1, eris0);

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
    @FXML
    protected Arc mercuryOrbit1;
    @FXML
    protected Arc venusOrbit1;
    @FXML
    protected Arc earthOrbit1;
    @FXML
    protected Arc marsOrbit1;
    @FXML
    protected Arc ceresOrbit1;
    @FXML
    protected Arc jupiterOrbit1;
    @FXML
    protected Arc saturnOrbit1;
    @FXML
    protected Arc uranusOrbit1;
    @FXML
    protected Arc neptuneOrbit1;
    @FXML
    protected Arc plutoOrbit1;
    @FXML
    protected Arc haumeaOrbit1;
    @FXML
    protected Arc makemakeOrbit1;
    @FXML
    protected Arc erisOrbit1;
    @FXML
    protected CheckBox orbitCheckBox;
    @FXML
    protected Button fullscreenButton;
}
