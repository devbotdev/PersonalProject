package me.ib.PersonalProject.simulator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.ib.PersonalProject.control.StarryBackground;
import me.ib.PersonalProject.simulator.bodies.*;
import me.ib.PersonalProject.simulator.bodies.dwarf.*;
import me.ib.PersonalProject.simulator.values.PlanetsC;
import me.ib.PersonalProject.util.Utility;

import java.awt.*;

public class Controller {

    public final PhongMaterial material = new PhongMaterial();
    private PhongMaterial glowMaterial;
    private Stage stage;
    private Planet sun0, mercury0, venus0, earth0, mars0, ceres0, jupiter0, saturn0, uranus0, neptune0, pluto0, haumea0, makemake0, eris0;
    private final PlanetsC planetsC = new PlanetsC();

    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 400, 150, 50, 95);

        initializePlanets();

        Platform.runLater(() -> {
            stage = (Stage) bgPane.getScene().getWindow();
            a = (int) (6000 * Utility.getStageScale(stage));
            resizePlanets(stage);
            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                resizePlanets(stage);
                setMoons();
            });
            stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                resizePlanets(stage);
                setMoons();
            });

            stage.fullScreenProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue) fullscreenButton.setVisible(true);
            });

            fullscreenButton.setVisible(false);

            hoveredPlanetName.setTranslateY(hoveredPlanetName.getLayoutBounds().getHeight());
        });

        hoveredPlanetName.setVisible(true);
        focusedView.setVisible(false);
    }

    public void setActions(Planet planet) {
        planet.getSphere().setOnMouseEntered(event -> shine(planet.getSphere()));
        planet.getSphere().setOnMouseExited(event -> unshine(planet.getSphere()));

        orbitCheckBox.setOnAction(this::toggleOrbit);
        fullscreenButton.setOnAction(event -> setFullscreen());
        planet.getSphere().setOnMouseClicked(event -> planetClicked(planet));
        backButtonFocused.setOnAction(event -> backButtonFocusedPressed());
    }

    private void backButtonFocusedPressed() {
        focusedView.setVisible(false);
        overallView.setVisible(true);

        updateHoveredPlanetName("Solar System");
    }

    private void planetClicked(Planet planet) {
        overallView.setVisible(false);
        focusedView.setVisible(true);

        focusedPlanet.setMaterial(planet.getSphere().getMaterial());
        updateHoveredPlanetName(planet.getSphere().getId().substring(0, 1).toUpperCase() + planet.getSphere().getId().substring(1));

        if (planet.moons == null) {
            moon.setVisible(false);
            moon1.setVisible(false);
            moon2.setVisible(false);
            moon3.setVisible(false);
            return;
        }

        if (planet.moons.length >= 1) {
            if (planet.moons[0] == null || planet.moons[0].getMaterial() == null) {
                moon.setVisible(false);
            } else {
                moon.setMaterial(planet.moons[0].getMaterial());
                moon.setVisible(true);
            }

            moon1.setVisible(false);
            moon2.setVisible(false);
            moon3.setVisible(false);
        }
        if (planet.moons.length >= 2) {
            if (planet.moons[1] == null || planet.moons[1].getMaterial() == null) {
                moon1.setVisible(false);
            } else {
                moon1.setMaterial(planet.moons[1].getMaterial());
                moon1.setVisible(true);
            }

            moon2.setVisible(false);
            moon3.setVisible(false);
        }
        if (planet.moons.length >= 3) {
            if (planet.moons[2] == null || planet.moons[2].getMaterial() == null) {
                moon2.setVisible(false);
            } else {
                moon2.setMaterial(planet.moons[2].getMaterial());
                moon2.setVisible(true);
            }

            moon3.setVisible(false);
        }
        if (planet.moons.length >= 4) {
            if (planet.moons[3] == null || planet.moons[3].getMaterial() == null) {
                moon3.setVisible(false);
            } else {
                moon3.setMaterial(planet.moons[3].getMaterial());
                moon3.setVisible(true);
            }
        }
    }

    private void setMoons() {
        double mid = focusedPlanet.getRadius() * 3.5;

        moon.setTranslateX(-mid / 1.3 * Utility.getStageXScale(stage));
        moon1.setTranslateX(mid / 1.3 * Utility.getStageXScale(stage));
        moon2.setTranslateX(-mid * 1.3 * Utility.getStageXScale(stage));
        moon3.setTranslateX(mid * 1.3 * Utility.getStageXScale(stage));
    }

    private void resizePlanets(Stage stage) {
        double width = stage.getWidth();
        double height = stage.getHeight();

        double centerX = width / 2;
        double centerY = height / 2;

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

    private int a;
    private int ofs = 0;
    private int planet1 = 0;

    private void updateOrbit(Arc topOrbit, Arc bottomOrbit, Planet planet) {
        double radiusX = Math.abs(sun.getLayoutX() - planet.getSphere().getLayoutX() + planet.getSphere().getRadius());
        topOrbit.setRadiusX(radiusX);
        topOrbit.setRadiusY(40 + radiusX / 8);
        topOrbit.setTranslateY((sun.getRadius() / -1.15) + ((9000 * Utility.getStageScale(stage) + ofs) / topOrbit.getRadiusX()));

        bottomOrbit.setRadiusX(radiusX);
        bottomOrbit.setRadiusY(40 + radiusX / 8);
        bottomOrbit.setTranslateY((sun.getRadius() / 1.15) - ((9000 * Utility.getStageScale(stage) + ofs) / topOrbit.getRadiusX()));

        if (planet1 >= 1) ofs = a;

        planet1++;
        if (planet1 == 13) {
            ofs = 0;
        }
    }

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

        planetsC.createOrbit(mercuryOrbit, mercuryOrbit1, mercury0);
        planetsC.createOrbit(venusOrbit, venusOrbit1, venus0);
        planetsC.createOrbit(earthOrbit, earthOrbit1, earth0);
        planetsC.createOrbit(marsOrbit, marsOrbit1, mars0);
        planetsC.createOrbit(ceresOrbit, ceresOrbit1, ceres0);
        planetsC.createOrbit(jupiterOrbit, jupiterOrbit1, jupiter0);
        planetsC.createOrbit(saturnOrbit, saturnOrbit1, saturn0);
        planetsC.createOrbit(uranusOrbit, uranusOrbit1, uranus0);
        planetsC.createOrbit(neptuneOrbit, neptuneOrbit1, neptune0);
        planetsC.createOrbit(plutoOrbit, plutoOrbit1, pluto0);
        planetsC.createOrbit(haumeaOrbit, haumeaOrbit1, haumea0);
        planetsC.createOrbit(makemakeOrbit, makemakeOrbit1, makemake0);
        planetsC.createOrbit(erisOrbit, erisOrbit1, eris0);

        setActions(sun0);
        setActions(mercury0);
        setActions(venus0);
        setActions(earth0);
        setActions(mars0);
        setActions(ceres0);
        setActions(jupiter0);
        setActions(saturn0);
        setActions(uranus0);
        setActions(neptune0);
        setActions(pluto0);
        setActions(eris0);
        setActions(haumea0);
        setActions(makemake0);
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
    protected Sphere focusedPlanet, moon, moon1, moon2, moon3;
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
    protected Button fullscreenButton, backButtonFocused;
    @FXML
    protected BorderPane overallView, focusedView;

    public void setFullscreen() {
        stage.setHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        stage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        stage.setFullScreen(true);
        fullscreenButton.setVisible(false);
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

        if (overallView.isVisible()) updateHoveredPlanetName("Solar System");
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
}
