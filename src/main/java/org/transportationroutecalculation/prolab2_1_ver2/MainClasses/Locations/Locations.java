package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public abstract class Locations {

    private Point2D.Double location;

    public Locations(Point2D.Double location) {
        this.location = location;
    }

    public Point2D.Double getLocation() {
        return location;
    }


    public abstract List<Map.Entry<String, Double>> nearestStation();

}