package org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BusStation.class, name = "bus"),
        @JsonSubTypes.Type(value = TramStation.class, name = "tram")
})
public abstract class Stations {

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
    private ArrayList<Transfer> transfer = new ArrayList<>();

    private static final ObjectMapper mapper = new ObjectMapper();  

    public Stations() {
        this.location = new Point2D.Double(0.0, 0.0);
        this.transfer = new ArrayList<>();
    }

    public Stations(String stationID, String stationType, Point2D.Double location, Boolean is_last_station) {
        this.stationID = stationID;
        this.stationType = stationType;
        this.location = location;
        this.is_last_station = is_last_station;
        this.transfer = new ArrayList<>();
    }

    public Stations(String stationID, String stationType, Point2D.Double location, Boolean is_last_station,
                    ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        this.stationID = stationID;
        this.stationType = stationType;
        this.location = location;
        this.is_last_station = is_last_station;
        this.next_stations = next_stations;
        this.transfer = (transfer != null) ? transfer : new ArrayList<>();
    }

    @JsonSetter("transfer")
    public void setTransfer(Object transfer) {
        System.out.println("setTransfer called with: " + transfer);
        if (transfer == null) {
            return;
        }
        if (transfer instanceof ArrayList<?> arrayList) {
            this.transfer = new ArrayList<>((ArrayList<Transfer>) arrayList);
        } else if (transfer instanceof LinkedHashMap<?, ?>) {
            Transfer singleTransfer = mapper.convertValue(transfer, Transfer.class);
            this.transfer.add(singleTransfer);
        } else if (transfer instanceof Transfer singleTransfer) {
            this.transfer.add(singleTransfer);
        } else {
            System.out.println("Unexpected transfer type: " + transfer.getClass());
        }
    }

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

    public ArrayList<Transfer> getTransfer() {
        return transfer;
    }

    public void setTransfer(ArrayList<Transfer> transfer) {
        this.transfer = (transfer != null) ? transfer : new ArrayList<>();
    }
}