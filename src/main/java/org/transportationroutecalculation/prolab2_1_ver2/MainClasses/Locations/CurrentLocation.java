package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

public class CurrentLocation extends Locations {

    public CurrentLocation() {
        super();
    }

    @JsonCreator
    public CurrentLocation(@JsonProperty("lat") double lat, @JsonProperty("lon") double lon) {
        super(new Point2D.Double(lat, lon));
    }
    @Override
    public List<Map.Entry<String, Double>> nearestStation() {
        return List.of();
    }


}

