package me.ib.PersonalProject.simulator;

import javafx.scene.paint.PhongMaterial;
import me.ib.PersonalProject.util.Utility;

public class Moon {

    public final Ring[] rings;
    private final int moonDiameter;
    private final long distanceFromPlanet;
    private final String name;
    private final PhongMaterial material = new PhongMaterial();
    public int TEMPERATURE_MAX, TEMPERATURE_MIN, TEMPERATURE_AVG;
    public double orbitTime;
    public long timeCreated = 4500000000L;
    private String dayTime;

    protected Moon(String name, Ring[] rings, int moonDiameter, long distanceFromPlanet, String dayTime, double orbitTime) {
        this.rings = rings;
        this.moonDiameter = moonDiameter;
        this.distanceFromPlanet = distanceFromPlanet;
        this.dayTime = dayTime;
        this.orbitTime = orbitTime;
        this.name = name;
    }

    public Moon(String name, Ring[] rings, int moonDiameter, long distanceFromPlanet) {
        this.rings = rings;
        this.moonDiameter = moonDiameter;
        this.name = name;
        this.distanceFromPlanet = distanceFromPlanet;
    }

    public void setObjectTexture(String s) {
        material.setDiffuseMap(Utility.getResourceAsImage("objectTextures/" + s));
    }

    public PhongMaterial getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public int getMoonDiameter() {
        return moonDiameter;
    }

    public long getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public String getDayTime() {
        return dayTime;
    }
}
