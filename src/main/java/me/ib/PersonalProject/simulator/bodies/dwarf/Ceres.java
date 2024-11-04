package me.ib.PersonalProject.simulator.bodies.dwarf;

import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Ceres extends Planet {

    public Ceres() {
        super(null, null, Utility.convertToNum(413, "million"),
                952, 1682, 9 + " hours", 0, "null");

        dwarfPlanet();

        setSunlightTravelTime(0, 22, 0);
    }
}
