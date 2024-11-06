package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Mars extends Planet {

    public Mars() {
        super(new Moon[2], null, Utility.convertToNum(227.9 , "million"),
                6780, 687, 24.6 + " hours", 25, "6.4 x 10^23");

        setSunlightTravelTime(0, 13, 0);

        setTemperature(27, -143);

        moons[0] = new Moon("Phobos", null, 0, 0);
        moons[1] = new Moon("Deimos", null, 0, 0);
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
