package me.ib.PersonalProject.simulator;

import com.sun.istack.internal.Nullable;

public class Moon {
    public final Ring[] rings;
    private final int moonDiameter;
    private final long distanceFromPlanet;
    private String dayTime;
    public int TEMPERATURE_MAX, TEMPERATURE_MIN, TEMPERATURE_AVG;
    public double orbitTime;
    public long timeCreated = 4500000000L;
    private final String name;

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
}
