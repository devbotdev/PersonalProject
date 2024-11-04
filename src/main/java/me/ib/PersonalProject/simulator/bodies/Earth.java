package me.ib.PersonalProject.simulator.bodies;

import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Earth extends Planet {

    public Earth() {
        super(new Moon[1], null, Utility.convertToNum(149.6, "million"),
                12742, 365.25, 23.9 + " hours", 23.4, "6 × 10^24");

        setSunlightTravelTime(0, 8, 0);

        setTemperature(70, -89, 15);

        moons[0] = new Moon("Moon", null, 1738, 384400);
    }
}
