package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

import java.awt.*;
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


    abstract List<Map.Entry<String, Double>> nearestStation();

}


class CurrentLocation extends Locations {

    public CurrentLocation(Point2D.Double location) {
        super(location);
    }

    @Override
    List<Map.Entry<String, Double>> nearestStation() {
        return List.of();
    }

}

class TargetLocation extends Locations {

    public TargetLocation(Point2D.Double location) {
        super(location);
    }

    @Override
    List<Map.Entry<String, Double>> nearestStation() {
        return List.of();
    }


}