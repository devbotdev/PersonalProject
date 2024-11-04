package me.ib.PersonalProject.simulator.bodies.dwarf;

import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Eris extends Planet {
    public Eris() {
        super(null, null, Utility.convertToNum(10, "billion"),
                2400, 557, 25.9 + " hours", 0, "null");

        setSunlightTravelTime(9, 0, 0);

        setTemperature(-217, -243);
    }
}