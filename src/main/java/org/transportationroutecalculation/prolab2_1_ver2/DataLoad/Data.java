package org.transportationroutecalculation.prolab2_1_ver2.DataLoad;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Vehicles.Taxi;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("city")
    private String city;

    @JsonProperty("taxi")
    private Taxi taxi;

    @JsonProperty("duraklar")
    private Stations[] stations;

    public Data() {
        // Default constructor required by Jackson
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

    public void setStations(Stations[] stations) {
        this.stations = stations;
    }
}