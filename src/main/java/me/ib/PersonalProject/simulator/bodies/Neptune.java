package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Neptune extends Planet {

    public Neptune(Sphere sphere) {
        super(new Moon[16], new Ring[9], Utility.convertToNum(4.503, "billion"),
                49528, 60190, 16 + " hours", 28, "1.02×10^26", sphere);

        setTemperature(0, -235, -210);

        moons[0] = new Moon("Triton", null, 2706, 354800);

        moons[0].setObjectTexture("triton.jpg");

        rings[0] = new Ring("Galle");
        rings[1] = new Ring("Leverrier");
        rings[2] = new Ring("Lassell");
        rings[3] = new Ring("Arago");
        rings[4] = new Ring("Adams");
        rings[5] = new Ring("Courage");
        rings[6] = new Ring("Liberté");
        rings[7] = new Ring("Egalité");
        rings[8] = new Ring("Fraternité");
    }

    @Override
    public Sphere getSphere() {
        return sphere;
    }

    @Override
    public void initializePlanet(Sphere sphere) {

    }
}
