package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.NextStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Transfer;

import java.util.ArrayList;

@Component
public class StationRelationshipMapper {

    public void mapRelationships(Data data) {
        mapTransferStations(data);
        mapNextStations(data);
    }

    private void mapTransferStations(Data data) {
        for (Stations station : data.getStations()) {
            ArrayList<Transfer> transfers = station.getTransfer();
            if (transfers != null && !transfers.isEmpty()) {
                for (Transfer transfer : transfers) {
                    String transferStopId = transfer.getTransferStopId();
                    Stations targetStation = data.getStationMap().get(transferStopId);
                    if (targetStation != null) {
                        transfer.setTransferStation(targetStation);
                    }
                }
            }
        }
    }

    private void mapNextStations(Data data) {
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