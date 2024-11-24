package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Makemake extends Planet {

    public Makemake(Sphere sphere) {
        super(new Moon[1], null, Utility.convertToNum(9.8, "billion"),
                1430, 111401, 22 + " hours", 0, "null", sphere);

        setSunlightTravelTime(6, 20, 0);

        moons[0] = new Moon("S/2015 (136472) 1, nicknamed MK 2", null, 160, 20900);

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
