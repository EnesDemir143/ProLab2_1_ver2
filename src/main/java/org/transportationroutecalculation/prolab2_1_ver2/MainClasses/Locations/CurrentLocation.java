package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class CurrentLocation extends Locations {

    public CurrentLocation() {
        super();
    }

    public CurrentLocation(Point2D.Double location) {
        super(location);
    }

    @Override
    public List<Map.Entry<String, Double>> nearestStation() {
        return List.of();
    }


}