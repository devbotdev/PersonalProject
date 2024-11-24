package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Pluto extends Planet {

    public Pluto(Sphere sphere) {
        super(new Moon[5], null, Utility.convertToNum(5.9, "billion"),
                2377, 90582, 153 + " hours", 57, "null", sphere);

        setSunlightTravelTime(5.5, 0, 0);

        setTemperature(-232);

        dwarfPlanet();
    }
    @Override
    public Sphere getSphere() {
        return sphere;
    }
    @Override
    public void initializePlanet(Sphere sphere) {
    }
}
