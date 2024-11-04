package me.ib.PersonalProject.simulator.bodies;

import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Neptune extends Planet {

    public Neptune() {
        super(new Moon[16], new Ring[9], Utility.convertToNum(4.503, "billion"),
                49528, 60190, 16 + " hours", 28, "1.02×10^26");

        setTemperature(0, -235, -210);
    }
}
