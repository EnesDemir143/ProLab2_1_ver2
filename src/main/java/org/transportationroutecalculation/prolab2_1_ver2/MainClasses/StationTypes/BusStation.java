package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BusStation extends Stations {

    public BusStation() {
        super();
    }

    public BusStation(String stationID, String stationType, Point2D.Double location, Boolean is_last_station) {
        super(stationID, stationType, location, is_last_station);
    }

    public BusStation(String stationID, String stationType, Point2D.Double location, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        super(stationID, stationType, location, is_last_station, next_stations, transfer);
    }
}