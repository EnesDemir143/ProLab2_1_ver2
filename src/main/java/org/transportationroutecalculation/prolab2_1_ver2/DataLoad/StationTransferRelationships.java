package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Transfer;

import java.util.ArrayList;

@Component
public class StationTransferRelationships implements RelationshipMapper{

    @Override
    public void map(Data data) {
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
}
