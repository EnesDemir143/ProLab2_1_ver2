package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("city")
    private String city;

    @JsonProperty("taxi")
    private Taxi taxi;

    @JsonProperty("duraklar")
    private Stations[] stations;

    private Map<String, Stations> stationMap;

    public Data() {
        this.stationMap = new HashMap<>();
    }

    public void setStations(Stations[] stations) {
        this.stations = stations;
        if (this.stations != null) {
            for (Stations station : stations) {
                this.stationMap.put(station.getStationID(), station);
            }
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public Stations[] getStations() {
        return stations;
    }

    public Map<String, Stations> getStationMap() {
        return stationMap;
    }
}