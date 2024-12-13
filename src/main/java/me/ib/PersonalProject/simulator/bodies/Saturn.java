package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Saturn extends Planet {

    public Saturn(Sphere sphere) {
        super(new Moon[146], new Ring[8], Utility.convertToNum(1.433, "billion"),
                120500, 10756, 10.7 + " hours", 26.73, "5.7 x 10^26", sphere);
        setSunlightTravelTime(0, 80, 0);

        setTemperature(-178);

        moons[0] = new Moon("Titan", null, 5150, 1221850);
        moons[1] = new Moon("Enceladus", null, 504, 238000);

        moons[0].setObjectTexture("titan.jpg");
        moons[1].setObjectTexture("enceladus.jpg");

        rings[0] = new Ring("D", 0);
        rings[1] = new Ring("C", 0);
        rings[2] = new Ring("B", 0);
        rings[3] = new Ring("A", 0);
        rings[4] = new Ring("F", 0);
        rings[5] = new Ring("G", 0);
        rings[6] = new Ring("E", 282000);
        rings[7] = new Ring("Phoebe", 282000);
    }

    @Override
    public Sphere getSphere() {
        return sphere;
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
