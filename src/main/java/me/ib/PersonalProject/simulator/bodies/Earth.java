package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Earth extends Planet {

    public Earth(Sphere sphere) {
        super(new Moon[1], null, Utility.convertToNum(149.6, "million"),
                12742, 365.25, 23.9 + " hours", 23.4, "6 × 10^24", sphere);

        setSunlightTravelTime(0, 8, 0);

        setTemperature(70, -89, 15);

        moons[0] = new Moon("Moon", null, 1738, 384400);
        moons[0].setObjectTexture("moon.jpg");
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }

    @Override
    public Sphere getSphere() {
        return sphere;
    }
}
