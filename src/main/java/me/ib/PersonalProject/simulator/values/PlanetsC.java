package me.ib.PersonalProject.simulator.values;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import me.ib.PersonalProject.simulator.Planet;

public class PlanetsC {
    public void createOrbit(Arc topOrbit, Arc bottomOrbit, Planet planet) {
        double radiusX = planet.getSphere().getLayoutX() + planet.getSphere().getRadius();

        topOrbit.setRadiusX(radiusX);
        topOrbit.setRadiusY(radiusX / 2);
        topOrbit.setStartAngle(0);
        topOrbit.setLength(180);

        bottomOrbit.setRadiusX(radiusX);
        bottomOrbit.setRadiusY(radiusX / 2);
        bottomOrbit.setStartAngle(180);
        bottomOrbit.setLength(180);

        topOrbit.setFill(null);
        topOrbit.setStroke(Color.GRAY);
        topOrbit.setStrokeWidth(1);

        bottomOrbit.setFill(null);
        bottomOrbit.setStroke(Color.GRAY);
        bottomOrbit.setStrokeWidth(1);

        topOrbit.setType(ArcType.OPEN);
        bottomOrbit.setType(ArcType.OPEN);
    }
}
