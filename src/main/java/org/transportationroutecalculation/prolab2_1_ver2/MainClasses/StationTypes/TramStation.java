package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TramStation extends Stations {

    public TramStation() {
        super();
    }

    public TramStation(String city,String stationID, String stationType, Point2D.Double location, Boolean is_last_station) {
        super(city,stationID, stationType, location, is_last_station);
    }

    public TramStation(String city,String stationID, String stationType, Point2D.Double location, Boolean is_last_station,
                       ArrayList<NextStation> next_stations, Transfer transfer) {
        super(city,stationID, stationType, location, is_last_station, next_stations, transfer);
    }
}