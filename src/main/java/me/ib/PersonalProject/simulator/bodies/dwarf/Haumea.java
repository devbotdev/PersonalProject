package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Haumea extends Planet {
    public Haumea(Sphere sphere) {
        super(null, new Ring[1], Utility.convertToNum(6.5, "billion"),
                1740, 285, 4 + " hours", 0, "null", sphere);

        setSunlightTravelTime(0, 0, 0);

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
