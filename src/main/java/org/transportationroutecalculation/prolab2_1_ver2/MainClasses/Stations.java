package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public abstract class Stations {

    private String stationID;
    private String stationType;
    private Point2D.Double location;
    private Boolean is_last_station;
    private ArrayList<NextStation> next_stations;
    private ArrayList<Transfer> transfer;

    public Stations(String stationID, String stationType, Point2D.Double location, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        this.stationID = stationID;
        this.stationType = stationType;
        this.location = location;
        this.is_last_station = is_last_station;
        this.next_stations = next_stations;
        this.transfer = transfer;
    }

    public Boolean getIs_last_station() {
        return is_last_station;
    }

    public Point2D.Double getLocation() {
        return location;
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationType() {
        return stationType;
    }
}

class BusStation extends Stations {


    public BusStation(String stationID, String stationType, Point2D.Double location, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        super(stationID, stationType, location, is_last_station, next_stations, transfer);
    }
}


class TramsStation extends Stations {


    public TramsStation(String stationID, String stationType, Point2D.Double location, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        super(stationID, stationType, location, is_last_station, next_stations, transfer);
    }
}


class NextStation {

    private Stations stations;
    private Double distance;
    private Double amount;
    private Integer time;

    public NextStation(Stations stations, Double distance, Double amount, Integer time) {
        this.stations = stations;
        this.distance = distance;
        this.amount = amount;
        this.time = time;
    }
}

class Transfer{

    private Stations transfer_station;
    private Double transfer_amount;
    private Integer transfer_time;

    public Transfer(Stations transfer_station, Double transfer_amount, Integer transfer_time) {
        this.transfer_station = transfer_station;
        this.transfer_amount = transfer_amount;
        this.transfer_time = transfer_time;
    }
}