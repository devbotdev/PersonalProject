package me.ib.PersonalProject.simulator;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.control.StarryBackground;
import me.ib.PersonalProject.simulator.bodies.Sun;
import me.ib.PersonalProject.util.Utility;

public class Controller {
    @FXML
    public Pane bgPane;
    @FXML
    public void initialize() {
        new StarryBackground(bgPane, 400, 150, 50, 95);

        initializePlanets();
    }
    private void initializePlanets() {
        Planet sunO = new Sun();
        sunO.initializePlanet(sun);
    }
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
    protected Sphere saturn;
    @FXML
    protected Sphere neptune;
    @FXML
    protected Sphere pluto;
    @FXML
    protected Sphere ceres;
    @FXML
    protected Sphere haumea;
    @FXML
    protected Sphere makemake;
}
