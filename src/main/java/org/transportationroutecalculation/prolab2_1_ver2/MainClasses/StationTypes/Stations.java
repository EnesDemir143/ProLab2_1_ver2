package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.geom.Point2D;
import java.util.ArrayList;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BusStation.class, name = "bus"),
        @JsonSubTypes.Type(value = TramStation.class, name = "tram")
})
public abstract class Stations {

    @JsonProperty("city")
    private String city;

    @JsonProperty("id")
    private String stationID;

    @JsonProperty("type")
    private String stationType;

    @JsonProperty("name")
    private String name;

    private Point2D.Double location;

    @JsonProperty("sonDurak")
    private Boolean is_last_station;

    @JsonProperty("nextStops")
    private ArrayList<NextStation> next_stations = new ArrayList<>();

    @JsonProperty("transfer")
    private Transfer transfer;

    public Stations() {

    }

    public Stations(String city,String stationID, String stationType, Point2D.Double location, Boolean is_last_station) {
        this.city = city;
        this.stationID = stationID;
        this.stationType = stationType;
        this.location = location;
        this.is_last_station = is_last_station;
    }

    public Stations(String city,String stationID, String stationType, Point2D.Double location, Boolean is_last_station,
                    ArrayList<NextStation> next_stations, Transfer transfer) {
        this.city = city;
        this.stationID = stationID;
        this.stationType = stationType;
        this.location = location;
        this.is_last_station = is_last_station;
        this.next_stations = next_stations;
        this.transfer = transfer;
    }

    // Getters and setters
    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point2D.Double getLocation() {
        return location;
    }

    public void setLocation(Point2D.Double location) {
        this.location = location;
    }

    @JsonProperty("lat")
    public void setLat(double lat) {
        if (this.location == null) {
            this.location = new Point2D.Double();
        }
        this.location.setLocation(lat, this.location.getY());
    }

    @JsonProperty("lon")
    public void setLon(double lon) {
        if (this.location == null) {
            this.location = new Point2D.Double();
        }
        this.location.setLocation(this.location.getX(), lon);
    }

    public Boolean getIs_last_station() {
        return is_last_station;
    }

    public void setIs_last_station(Boolean is_last_station) {
        this.is_last_station = is_last_station;
    }

    public ArrayList<NextStation> getNext_stations() {
        return next_stations;
    }

    public void setNext_stations(ArrayList<NextStation> next_stations) {
        this.next_stations = next_stations;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}