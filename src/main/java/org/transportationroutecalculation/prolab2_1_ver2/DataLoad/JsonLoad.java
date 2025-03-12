package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

import java.io.File;

public class JsonLoad {
    private String path;

    public JsonLoad(String path) {
        this.path = path;
    }

    public double load() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Data data = objectMapper.readValue(new File(path), Data.class);
            Taxi taxi = data.getTaxi();
            double distance = 10.0; // Example distance
            double totalCost = taxi.calculate_price(distance);
            System.out.println("City: " + data.getCity());
            System.out.println("Opening Fee: " + taxi.getOpening_fee());
            System.out.println("Cost per KM: " + taxi.getCost_per_km());
            System.out.println("Stations loaded: " + data.getStations().length);
            for (Stations station : data.getStations()) {
                System.out.println("Station: " + station.getStationID() +
                        ", Name: " + station.getName() +
                        ", Type: " + station.getStationType() +
                        ", Location: (" + station.getLocation().getX() + ", " + station.getLocation().getY() + ")" +
                        ", " + station.getNext_stations().size() + " next stations");
            }
            return totalCost;

        } catch (Exception e) {
            throw new RuntimeException("Error loading JSON from: " + path + ". " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        JsonLoad jsonLoad = new JsonLoad("src/main/java/org/transportationroutecalculation/prolab2_1_ver2/Data/veriseti.json");
        double result = jsonLoad.load();
        System.out.println("Toplam maliyet: " + result);
    }
}