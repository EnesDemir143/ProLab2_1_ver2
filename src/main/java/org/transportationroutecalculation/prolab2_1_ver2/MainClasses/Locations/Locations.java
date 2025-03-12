package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public abstract class Locations {

    private Point2D.Double location;

    public Locations() {
        this.location = new Point2D.Double(0.0, 0.0);
    }

    @JsonCreator
    public Locations(@JsonProperty("lat") double lat, @JsonProperty("lon") double lon) {
        this.location = new Point2D.Double(lat, lon); // lat -> x, lon -> y
    }

    public Locations(Point2D.Double location) {
        this.location = location;
    }

    public Point2D.Double getLocation() {
        return location;
    }


    public abstract List<Map.Entry<String, Double>> nearestStation();

}