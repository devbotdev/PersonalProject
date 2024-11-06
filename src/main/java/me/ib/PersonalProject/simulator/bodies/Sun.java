package me.ib.PersonalProject.simulator.bodies;

import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import me.ib.PersonalProject.simulator.Planet;
import me.ib.PersonalProject.util.Utility;

public class Sun extends Planet {
    public Sun() {
        super(null, null, Utility.convertToNum(0, ""),
                1391000, "230 million years", 25 + " days at the equator, " + 36 + " days at the poles", 7.25, "2 × 10^30");

        setTemperature(15000000, 5500, 2000000);
    }

    @Override
    public void initializePlanet(Sphere sphere) {
        PhongMaterial sunM = new PhongMaterial();
        sunM.setDiffuseMap(Utility.getResourceAsImage("objectTextures/sunmap.jpg"));
        sphere.setMaterial(sunM);
    }
}