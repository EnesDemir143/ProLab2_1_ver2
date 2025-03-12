package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.BusStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.TramStation;

import java.awt.geom.Point2D;
import java.util.HashMap;

public interface StationFactory {

    Stations createStations(HashMap<String, Object> data);
}

class BusStationFactory implements StationFactory {

    @Override
    public Stations createStations(HashMap<String, Object> data) {

        return new BusStation(
                (String) data.get("id"),
                (String) data.get("type"),
                new Point2D.Double((Double) data.get("lat"), (Double) data.get("lon")),
                (Boolean) data.get("sonDurak")
        );
    }
}

class TramStationFactory implements StationFactory {

    @Override
    public Stations createStations(HashMap<String, Object> data) {

        return new TramStation(
                (String) data.get("id"),
                (String) data.get("type"),
                new Point2D.Double((Double) data.get("lat"), (Double) data.get("lon")),
                (Boolean) data.get("sonDurak")
        );
    }
}