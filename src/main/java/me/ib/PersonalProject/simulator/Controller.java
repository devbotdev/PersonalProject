package me.ib.PersonalProject.simulator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.ib.PersonalProject.control.StarryBackground;
import me.ib.PersonalProject.simulator.bodies.*;
import me.ib.PersonalProject.simulator.bodies.dwarf.*;
import me.ib.PersonalProject.simulator.values.PlanetsC;
import me.ib.PersonalProject.util.Utility;

import java.awt.*;
import java.util.Objects;

public class Controller {

    public final PhongMaterial material = new PhongMaterial();
    private final PlanetsC planetsC = new PlanetsC();
    @FXML
    public Pane bgPane;
    @FXML
    public Group planetsGroup;
    public String focusedPlanetId, moonId, moon1Id, moon2Id, moon3Id, focusedPlanetBack;
    @FXML
    protected Sphere glowSphere;
    @FXML
    protected Sphere sunShine;
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
    protected Button fullscreenButton, backButtonFocused, informationButton;
    @FXML
    protected TextArea informationPanel;
    @FXML
    protected BorderPane overallView, focusedView;
    @FXML
    protected Text stats;
    private PhongMaterial glowMaterial;
    private Stage stage;
    private Planet sun0, mercury0, venus0, earth0, mars0, ceres0, jupiter0, saturn0, uranus0, neptune0, pluto0, haumea0, makemake0, eris0;
    private boolean isFocusedOnMoon;
    private int a;
    private int ofs = 0;
    private int planet1 = 0;

    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 95);

        initializePlanets();

        Platform.runLater(() -> {
            stage = (Stage) bgPane.getScene().getWindow();
            informationPanel.setVisible(false);

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

            orbitCheckBox.setOnAction(e -> toggleOrbit());
            fullscreenButton.setOnAction(event -> setFullscreen());
            backButtonFocused.setOnAction(event -> backButtonFocusedPressed());

            hoveredPlanetName.setTranslateY(hoveredPlanetName.getLayoutBounds().getHeight());

            informationButton.setOnAction(event -> {
                informationPanel.setText(Utility.extractText(focusedPlanetId));
                informationPanel.setVisible(!informationPanel.isVisible());
            });
        });

        hoveredPlanetName.setVisible(true);
        focusedView.setVisible(false);
    }

    private boolean frozenState() {
        return informationPanel.isVisible();
    }

    public void setActions(Planet planet) {
        planet.getSphere().setOnMouseEntered(event -> shine(planet.getSphere()));
        planet.getSphere().setOnMouseExited(event -> unshine(planet.getSphere()));
        planet.getSphere().setOnMouseClicked(event -> planetClicked(planet));
    }

    public void setActions(Sphere sphere) {
        sphere.setOnMouseEntered(event -> shine(sphere));
        sphere.setOnMouseExited(event -> unshine(sphere));
        sphere.setOnMouseClicked(event -> moonClicked(sphere));
    }

    private void backButtonFocusedPressed() {
        if (frozenState()) {
            informationPanel.setVisible(false);
        }

        if (isFocusedOnMoon) {
            focusedOnMoon(false);
            planetClicked(Objects.requireNonNull(getPlanetFromName(focusedPlanetBack)));
        } else {
            focusedView.setVisible(false);
            overallView.setVisible(true);

            updateHoveredPlanetName("Solar System");
        }
    }

    private Planet getPlanetFromName(String name) {
        if (name.equalsIgnoreCase(sun.getId())) {
            return sun0;
        } else if (name.equalsIgnoreCase(mercury.getId())) {
            return mercury0;
        } else if (name.equalsIgnoreCase(venus.getId())) {
            return venus0;
        } else if (name.equalsIgnoreCase(earth.getId())) {
            return earth0;
        } else if (name.equalsIgnoreCase(mars.getId())) {
            return mars0;
        } else if (name.equalsIgnoreCase(jupiter.getId())) {
            return jupiter0;
        } else if (name.equalsIgnoreCase(saturn.getId())) {
            return saturn0;
        } else if (name.equalsIgnoreCase(uranus.getId())) {
            return uranus0;
        } else if (name.equalsIgnoreCase(neptune.getId())) {
            return neptune0;
        } else if (name.equalsIgnoreCase(ceres.getId())) {
            return ceres0;
        } else if (name.equalsIgnoreCase(pluto.getId())) {
            return pluto0;
        } else if (name.equalsIgnoreCase(haumea.getId())) {
            return haumea0;
        } else if (name.equalsIgnoreCase(makemake.getId())) {
            return makemake0;
        } else if (name.equalsIgnoreCase(eris.getId())) {
            return eris0;
        }
        return null;
    }

    private void focusedOnMoon(boolean b) {
        isFocusedOnMoon = b;
    }

    private void moonClicked(Sphere sphere) {
        informationButton.setVisible(!(Objects.equals(focusedPlanetId.toLowerCase(), neptune.getId().toLowerCase()) ||
                Objects.equals(focusedPlanetId.toLowerCase(), makemake.getId().toLowerCase())));

        focusedOnMoon(true);
        Planet pl = getPlanetFromName(focusedPlanetId);
        assert pl != null;

        focusedPlanetBack = focusedPlanetId;

        if (sphere == moon) {
            focusedPlanetId = pl.moons[0].getName();
            stats.setText(getMoonText(pl.moons[0]));
        } else if (sphere == moon1) {
            focusedPlanetId = pl.moons[1].getName();
            stats.setText(getMoonText(pl.moons[1]));
        } else if (sphere == moon2) {
            focusedPlanetId = pl.moons[2].getName();
            stats.setText(getMoonText(pl.moons[2]));
        } else if (sphere == moon3) {
            focusedPlanetId = pl.moons[3].getName();
            stats.setText(getMoonText(pl.moons[3]));
        }

        updateHoveredPlanetName(focusedPlanetId);

        focusedPlanet.setMaterial(sphere.getMaterial());

        moon.setVisible(false);
        moon1.setVisible(false);
        moon2.setVisible(false);
        moon3.setVisible(false);
    }

    private String getMoonText(Moon moon) {
        String s = "";
        if (moon == makemake0.moons[0]) s += "No Accurate Texture Map(s) Available (Limitation: Size & Distance From Earth)\nInformation Included In Makemake's Information Panel\n";
        if (moon == neptune0.moons[0]) s += "Information Included In Neptune's Information Panel\n";
        if (moon.rings != null) s += "Rings: " + moon.rings.length + "\n";
        if (moon.getMoonDiameter() != 0) s += "Diameter: " + Utility.formatWithCommas(moon.getMoonDiameter()) + "km" + "\n";
        if (moon.getDistanceFromPlanet() != 0) s += "Distance From the Planet: " + Utility.formatWithCommas(moon.getDistanceFromPlanet()) + "km" + "\n";
        if (moon.orbitTime != 0) s += "Orbit Time: " + moon.orbitTime + " days" + "\n";
        if (moon.getDayTime() != null) s += "Day Time: " + moon.getDayTime() + "\n";
        if (moon.TEMPERATURE_AVG != 0) s += "Average Temperature: " + moon.TEMPERATURE_AVG + "°C" + "\n";
        if (moon.TEMPERATURE_MAX != 0) s += "Max Temperature: " + moon.TEMPERATURE_MAX + "°C" + "\n";
        if (moon.TEMPERATURE_MIN != 0) s += "Min Temperature: " + moon.TEMPERATURE_MIN + "°C" + "\n";
        if (moon.timeCreated != 4500000000L) s += "Time Created: " + Utility.formatWithCommas(moon.timeCreated) + " years ago" + "\n";

        return s.substring(0, s.length() - 1);
    }

    private int getMoonsAmount() {
        if (Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).moons == null) return 0;
        return Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).moons.length;
    }

    private int getRingsAmount() {
        if (Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).rings == null) return 0;
        return Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).rings.length;
    }

    private String getOrbitTime() {
        if (Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getOrbitTime() != 0)
            return Utility.formatWithCommas((long) Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getOrbitTime()) + " days";
        else if (Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getOrbitString() != null)
            return Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getOrbitString();
        else return null;
    }

    private void planetClicked(Planet planet) {
        informationButton.setVisible(true);
        focusedPlanetId = planet.getSphere().getId().substring(0, 1).toUpperCase() + planet.getSphere().getId().substring(1);

        StringBuilder s = new StringBuilder("Moons: " + getMoonsAmount() + "\n");

        if (planet.rings != null) {
            if (planet.rings[0] != null) {
                s.append("Rings: ").append(getRingsAmount()).append(" (");
                for (Ring r : planet.rings) {
                    if (r.getName() == null) break;
                    s.append(r.getName()).append(", ");
                }
                s = new StringBuilder(s.substring(0, s.length() - 2) + ")\n");
            } else {
                s.append("Rings: ").append(getRingsAmount()).append("\n");
            }
        } else s.append("Rings: 0\n");

        s.append("Distance From the Sun: ").append(Utility.formatWithCommas(Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getDistanceFromTheSun())).append("km").append("\n").append("Diameter: ").append(Utility.formatWithCommas(Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getPlanetDiameter())).append("km").append("\n").append("Orbit Time: ").append(getOrbitTime()).append("\n").append("Day Time: ").append(Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).getDayTime()).append("\n").append("Axis of Rotation Tilt: ").append(Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).axisOfRotationTilt).append("°C").append("\n").append("Time Created: ").append(Utility.formatWithCommas(planet.timeCreated)).append(" years ago").append("\n");

        if (!planet.mass.equals("null kg") && !planet.mass.equals(" kg"))
            s.append("Mass: ").append(Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).mass).append("\n");
        if (planet.getSunlightTravelTime() != null)
            s.append("Sunlight Travel Time: ").append(planet.getSunlightTravelTime()).append("\n");
        if (planet.getAvgTemperature() != 0)
            s.append("Average Temperature: ").append(Utility.formatWithCommas(planet.getAvgTemperature())).append("°C").append("\n");
        if (planet.getMaxTemperature() != 0)
            s.append("Max Temperature: ").append(Utility.formatWithCommas(planet.getMaxTemperature())).append("°C").append("\n");
        if (planet.getMinTemperature() != 0)
            s.append("Min Temperature: ").append(Utility.formatWithCommas(planet.getMinTemperature())).append("°C").append("\n");

        stats.setText(s.substring(0, s.length() - 1));

        overallView.setVisible(false);
        focusedView.setVisible(true);

        focusedPlanet.setMaterial(planet.getSphere().getMaterial());

        updateHoveredPlanetName();

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
                moonId = planet.moons[0].getName();
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
                moon1Id = planet.moons[1].getName();
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
                moon2Id = planet.moons[2].getName();
            }

            moon3.setVisible(false);
        }
        if (planet.moons.length >= 4) {
            if (planet.moons[3] == null || planet.moons[3].getMaterial() == null) {
                moon3.setVisible(false);
            } else {
                moon3.setMaterial(planet.moons[3].getMaterial());
                moon3.setVisible(true);
                moon3Id = planet.moons[3].getName();
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

        setActions(moon);
        setActions(moon1);
        setActions(moon2);
        setActions(moon3);
    }

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

        if (isMoon(sphere)) {
            updateHoveredPlanetName(getMoonId(sphere));
            return;
        }

        updateHoveredPlanetName(sphere.getId().substring(0, 1).toUpperCase() + sphere.getId().substring(1));
    }

    private String getMoonId(Sphere sphere) {
        if (Objects.equals(sphere.getId(), moon.getId())) {
            return moonId;
        } else if (Objects.equals(sphere.getId(), moon1.getId())) {
            return moon1Id;
        } else if (Objects.equals(sphere.getId(), moon2.getId())) {
            return moon2Id;
        } else if (Objects.equals(sphere.getId(), moon3.getId())) {
            return moon3Id;
        } else return null;
    }

    private boolean isMoon(Sphere sphere) {
        return Objects.equals(sphere.getId(), moon.getId()) || Objects.equals(sphere.getId(), moon1.getId()) || Objects.equals(sphere.getId(), moon2.getId()) || Objects.equals(sphere.getId(), moon3.getId());
    }

    public void updateHoveredPlanetName(String planetName) {
        hoveredPlanetName.setText(planetName);
    }

    public void updateHoveredPlanetName() {
        try {
            if (!Objects.requireNonNull(getPlanetFromName(focusedPlanetId)).isDwarf())
                hoveredPlanetName.setText(focusedPlanetId);
            else hoveredPlanetName.setText("Dwarf Planet: " + focusedPlanetId);
        } catch (NullPointerException ignored) {
        }
    }

    protected void unshine(Sphere sphere) {
        if (sphere == sun) {
            sunShine.setVisible(false);
        } else {
            glowSphere.setVisible(false);
        }

        if (overallView.isVisible()) {
            updateHoveredPlanetName("Solar System");
        } else if (focusedView.isVisible()) {
            updateHoveredPlanetName();
        }
    }

    private void toggleOrbit() {
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
