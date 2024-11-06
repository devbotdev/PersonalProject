package me.ib.PersonalProject.simulator.bodies.dwarf;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Haumea extends Planet {
    public Haumea() {
        super(null, new Ring[1], Utility.convertToNum(6.5, "billion"),
                1740, 285, 4 + " hours", 0, "null");

        setSunlightTravelTime(0, 0, 0);
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
