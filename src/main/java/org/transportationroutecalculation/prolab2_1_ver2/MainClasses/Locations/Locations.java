package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CurrentLocation.class, name = "current"),
        @JsonSubTypes.Type(value = TargetLocation.class, name = "target")
})
public abstract class Locations {

    private Point2D.Double location;

    public Locations() {
        this.location = new Point2D.Double(0.0, 0.0);
    }

    public Locations(Point2D.Double location) {
        this.location = location;
    }

    public Point2D.Double getLocation() {
        return location;
    }


    public abstract List<Map.Entry<String, Double>> nearestStation();

}