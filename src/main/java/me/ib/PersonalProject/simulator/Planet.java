package me.ib.PersonalProject.simulator;

/*
    public () {
        super(null, null, Utility.convertToNum(, ""),
                diameter, orbitTime, dayTime + " hours", axisOfRotationTilt, planetMass);

        setSunlightTravelTime(0, 0, 0);

        setTemperature(Max, Min, Avg);
    }
 */

import com.sun.istack.internal.Nullable;

public abstract class Planet {
    private boolean isDwarf = false;
    public final Moon[] moons;
    public final Ring[] rings;
    private final int planetDiameter;
    private final long distanceFromTheSun;
    private final String dayTime;
    private double TEMPERATURE_MAX, TEMPERATURE_MIN, TEMPERATURE_AVG;
    private int sunlightTravelTimeHours, sunlightTravelTimeMinutes, sunlightTravelTimeSeconds;
    public final double axisOfRotationTilt, orbitTime;
    public long timeCreated = 4500000000L;
    public final String mass;

    protected Planet(Moon[] moons, Ring[] rings, long distanceFromTheSun, int planetDiameter, double orbitTime, String dayTime, double axisOfRotationTilt, @Nullable String mass) {
        this.moons = moons;
        this.rings = rings;
        this.distanceFromTheSun = distanceFromTheSun;
        this.planetDiameter = planetDiameter;
        this.orbitTime = orbitTime;
        this.dayTime = dayTime;
        this.axisOfRotationTilt = axisOfRotationTilt;
        this.mass = mass + " kg";
    }

    public void setTemperature(double max, double min, double avg) {
        if (max != 0)
            this.TEMPERATURE_MAX = max;
        if (min != 0)
            this.TEMPERATURE_MIN = min;
        if (avg != 0)
            this.TEMPERATURE_AVG = avg;
    }
    public void setTemperature(double max, double min) {
        if (max != 0)
            this.TEMPERATURE_MAX = max;
        if (min != 0)
            this.TEMPERATURE_MIN = min;
    }

    public void setTemperature(double avg) {
        if (avg != 0)
            this.TEMPERATURE_AVG = avg;
    }

    public Moon[] getMoons() {
        return moons;
    }

    public long getDistanceFromTheSun() {
        return distanceFromTheSun;
    }

    public Ring[] getRings() {
        return rings;
    }

    public int getPlanetDiameter() {
        return planetDiameter;
    }

    public int getPlanetRadius() {
        return planetDiameter / 2;
    }

    public double getOrbitTime() {
        return orbitTime;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void dwarfPlanet() {
        isDwarf = true;
    }

    public String getSunlightTravelTime() {
        if (sunlightTravelTimeHours == 0) {
            if (sunlightTravelTimeSeconds == 0) return sunlightTravelTimeMinutes + " minutes";
            return sunlightTravelTimeMinutes + " minutes and " + sunlightTravelTimeSeconds + " seconds";
        } else {
            if (sunlightTravelTimeSeconds == 0) {
                return sunlightTravelTimeHours + " hours and " + sunlightTravelTimeMinutes + " minutes";
            } else {
                return sunlightTravelTimeHours + " hours " + sunlightTravelTimeMinutes + " minutes and " + sunlightTravelTimeSeconds + " seconds";
            }
        }
    }

    public void setSunlightTravelTime(double hours, double minutes, double seconds) {
        int totalHours = (int) hours;
        double remainingMinutes = (hours - totalHours) * 60 + minutes;

        int totalMinutes = (int) remainingMinutes;
        double remainingSeconds = (remainingMinutes - totalMinutes) * 60 + seconds;

        int totalSeconds = (int) remainingSeconds;

        if (totalSeconds >= 60) {
            totalMinutes += totalSeconds / 60;
            totalSeconds %= 60;
        }

        if (totalMinutes >= 60) {
            totalHours += totalMinutes / 60;
            totalMinutes %= 60;
        }

        sunlightTravelTimeHours = totalHours;
        sunlightTravelTimeMinutes = totalMinutes;
        sunlightTravelTimeSeconds = totalSeconds;
    }

    public boolean isDwarf() {
        return isDwarf;
    }

    public double getMaxTemperature() {
        return TEMPERATURE_MAX;
    }

    public double getMinTemperature() {
        return TEMPERATURE_MIN;
    }

    public double getAvgTemperature() {
        return TEMPERATURE_AVG;
    }
}
