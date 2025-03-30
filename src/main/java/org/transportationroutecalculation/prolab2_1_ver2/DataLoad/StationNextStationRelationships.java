package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.NextStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;

@Component
public class StationNextStationRelationships  implements RelationshipMapper{

    @Override
    public void map(Data data) {
        for (Stations station : data.getStations()) {
            ArrayList<NextStation> nextStations = station.getNext_stations();
            if (nextStations != null && !nextStations.isEmpty()) {
                for (NextStation nextStation : nextStations) {
                    String nextStationStopId = nextStation.getStopId();
                    Stations targetStation = data.getStationMap().get(nextStationStopId);
                    if (targetStation != null) {
                        nextStation.setNextstation(targetStation);
                    }
                }
            }
        }
    }
}