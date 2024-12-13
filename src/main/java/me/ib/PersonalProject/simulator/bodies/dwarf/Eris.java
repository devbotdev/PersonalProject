package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Eris extends Planet {
    public Eris(Sphere sphere) {
        super(null, null, Utility.convertToNum(10, "billion"),
                2400, 557, 25.9 + " hours", 0, "null", sphere);

        setSunlightTravelTime(9, 0, 0);

        setTemperature(-217, -243);

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
