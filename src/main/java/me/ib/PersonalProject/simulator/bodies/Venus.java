package me.ib.PersonalProject.simulator.bodies;

import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Venus extends Planet {

    public Venus() {
        super(null, null, Utility.convertToNum(108.2, "million"),
                12104, 225, 243 + " Earth Days", 3, "4.868×10^24");

        setSunlightTravelTime(0, 6, 0);

        setTemperature(475, 0, 462);
    }
}
