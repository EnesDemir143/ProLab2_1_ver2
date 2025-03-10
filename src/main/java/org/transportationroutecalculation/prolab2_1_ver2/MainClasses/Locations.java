package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

import java.util.List;
import java.util.Map;

public abstract class Locations {

    private Float lat;
    private Float lon;

    public Locations(Float lat, Float lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Float[] get_current_location(){
        return new Float[]{lat,lon};
    }

    abstract List<Map.Entry<String, Double>> nearestStation();

}


class CurrentLocation extends Locations {

    public CurrentLocation(Float lat, Float lon) {
        super(lat, lon);
    }

    @Override
    List<Map.Entry<String, Double>> nearestStation() {
        return List.of();
    }


}