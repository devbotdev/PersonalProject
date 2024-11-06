package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Mercury extends Planet {
    protected Mercury() {
        super(null, null, Utility.convertToNum(57.91, "million"),
                4879, 88, 176 + " Earth Days", 0.04, "3.30 x 10^23");

        setTemperature(430, -180, 167);

        setSunlightTravelTime(0, 3.2, 0);
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
