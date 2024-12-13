package me.ib.PersonalProject.simulator;

public class Ring {
    private final String name;
    private long distanceFromPlanet;

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
