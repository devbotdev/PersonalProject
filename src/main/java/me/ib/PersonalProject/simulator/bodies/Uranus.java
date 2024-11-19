package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Uranus extends Planet {

    public Uranus(Sphere sphere) {
        super(new Moon[28], new Ring[13], Utility.convertToNum(2.877, "billion"),
                51118, 30687, 17 + " hours", 97.77, "8.68 x 10^25", sphere);

        setSunlightTravelTime(2, 40, 0);

        setTemperature(0, -224.2, 0);

        rings[0] = new Ring("Zeta");
        rings[1] = new Ring("6");
        rings[2] = new Ring("5");
        rings[3] = new Ring("4");
        rings[4] = new Ring("Alpha");
        rings[5] = new Ring("Beta");
        rings[6] = new Ring("Eta");
        rings[7] = new Ring("Gamma");
        rings[8] = new Ring("Delta");
        rings[9] = new Ring("Lambda");
        rings[10] = new Ring("Epsilon");
        rings[11] = new Ring("Nu");
        rings[12] = new Ring("Mu");
    }
    @Override
    public Sphere getSphere() {
        return sphere;
    }
    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
