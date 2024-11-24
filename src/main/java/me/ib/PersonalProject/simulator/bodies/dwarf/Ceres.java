package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Ceres extends Planet {

    public Ceres(Sphere sphere) {
        super(null, null, Utility.convertToNum(413, "million"),
                952, 1682, 9 + " hours", 0, "null", sphere);

        setSunlightTravelTime(0, 22, 0);

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
