package me.ib.PersonalProject.simulator;

public class Ring {
    private long distanceFromPlanet;
    private final String name;
    public Ring(String name, long distanceFromPlanet) {
        this.distanceFromPlanet = distanceFromPlanet;
        this.name = name;
    }

    public Ring(String name) {
        this.name = name;
    }

    public long getDistanceFromPlanet() {
        return distanceFromPlanet;
    }

    public String getName() {
        return name;
    }
}
