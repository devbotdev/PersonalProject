package me.ib.PersonalProject.simulator;

public abstract class Planet {
    private final Moon[] moons;
    private final Ring[] rings;
    private final int distanceFromTheSun, planetDiameter, orbitTime, dayTime, planetTemperature;

    protected Planet(Moon[] moons, Ring[] rings, int distanceFromTheSun, int planetDiameter, int orbitTime, int dayTime, int planetTemperature) {
        this.moons = moons;
        this.rings = rings;
        this.distanceFromTheSun = distanceFromTheSun;
        this.planetDiameter = planetDiameter;
        this.orbitTime = orbitTime;
        this.dayTime = dayTime;
        this.planetTemperature = planetTemperature;
    }

    public Moon[] getMoons() {
        return moons;
    }

    public int getDistanceFromTheSun() {
        return distanceFromTheSun;
    }

    public Ring[] getRings() {
        return rings;
    }

    public int getPlanetDiameter() {
        return planetDiameter;
    }

    public int getOrbitTime() {
        return orbitTime;
    }

    public int getDayTime() {
        return dayTime;
    }

    public int getPlanetTemperature() {
        return planetTemperature;
    }
}
