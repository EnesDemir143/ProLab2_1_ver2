package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.NextStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Transfer;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

import java.io.File;
import java.util.ArrayList;

@Service
public class JsonLoad {

    @Value("${json.file.path}")
    private String path;
    private Data data;

    private final ObjectMapper objectMapper;

    public JsonLoad(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        load();
    }

    public double load() {
        try {
            data = objectMapper.readValue(new File(path), Data.class);
            Taxi taxi = data.getTaxi();
            double distance = 10.0;
            double totalCost = taxi.calculate_price(distance);
            System.out.println("City: " + data.getCity());
            System.out.println("Opening Fee: " + taxi.getOpening_fee());
            System.out.println("Cost per KM: " + taxi.getCost_per_km());
            System.out.println("Stations loaded: " + data.getStations().length);

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

            for (Stations station : data.getStations()) {
                System.out.print("Station: " + station.getStationID() +
                        ", Name: " + station.getName() +
                        ", Type: " + station.getStationType() +
                        ", Location: (" + station.getLocation().getX() + ", " + station.getLocation().getY() + ")" +
                        ", Transfers: ");
                ArrayList<Transfer> transfers = station.getTransfer();
                if (transfers != null && !transfers.isEmpty()) {
                    System.out.println(transfers.getFirst().toString());
                } else {
                    System.out.println("No transfers available");
                }
                ArrayList<NextStation> nextStations = station.getNext_stations();
                if (nextStations != null && !nextStations.isEmpty()) {
                    System.out.println(nextStations.toString());
                } else {
                    System.out.println("No nextStation available");
                }
            }

            return totalCost;

        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON from: " + path + ". " + e.getMessage(), e);
        }
    }

    public Data getData() {
        return data;
    }
}