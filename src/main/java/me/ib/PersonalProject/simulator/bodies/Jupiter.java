package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Moon;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.simulator.Ring;
import me.ib.PersonalProject.util.Utility;

public class Jupiter extends Planet {

    public Jupiter(Sphere sphere) {
        super(new Moon[95], new Ring[4], Utility.convertToNum(778.5, "million"),
                139822, 4333, 10.5 + " hours", 3, "1.9 × 10^27", sphere);

        moons[0] = new Moon("Ganymede", null, 5262, 1070400);
        moons[1] = new Moon("Callisto", null,4821,1882700);
        moons[2] = new Moon("Io", null,3643, 421800 );
        moons[3] = new Moon("Europa", null,3122,671100);

        moons[0].setObjectTexture("ganymede.jpg");
        moons[1].setObjectTexture("Callisto.jpg");
        moons[2].setObjectTexture("io.jpg");
        moons[3].setObjectTexture("europa.jpg");

        setTemperature(-110);

        setSunlightTravelTime(0, 43, 0);
    }

    @Override
    public void initializePlanet(Sphere sphere) {
    }

    @Override
    public Sphere getSphere() {
        return sphere;
    }
}
