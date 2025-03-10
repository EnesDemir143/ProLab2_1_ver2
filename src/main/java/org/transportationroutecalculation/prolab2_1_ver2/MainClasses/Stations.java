package org.transportationroutecalculation.prolab2_1_ver2.MainClasses;

import java.util.ArrayList;

public abstract class Stations {

    private String stationID;
    private String stationType;
    private Float lat;
    private Float lon;
    private Boolean is_last_station;
    private ArrayList<NextStation> next_stations;
    private ArrayList<Transfer> transfer;

    public Stations(String stationID, String stationType, Float lat, Float lon, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        this.stationID = stationID;
        this.stationType = stationType;
        this.lat = lat;
        this.lon = lon;
        this.is_last_station = is_last_station;
        this.next_stations = next_stations;
        this.transfer = transfer;
    }

    public Boolean getIs_last_station() {
        return is_last_station;
    }

    public double[] getLocation() {
        return new double[]{lat, lon};
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationType() {
        return stationType;
    }
}

class BusStation extends Stations {

    public BusStation(String stationID, String stationType, Float lat, Float lon, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        super(stationID, stationType, lat, lon, is_last_station, next_stations, transfer);
    }
}


class TramsStation extends Stations {

    public TramsStation(String stationID, String stationType, Float lat, Float lon, Boolean is_last_station, ArrayList<NextStation> next_stations, ArrayList<Transfer> transfer) {
        super(stationID, stationType, lat, lon, is_last_station, next_stations, transfer);
    }
}


class NextStation {

    private Stations stations;
    private Float distance;
    private Float amount;
    private Integer time;

    public NextStation(Stations stations, Float distance, Float amount, Integer time) {
        this.stations = stations;
        this.distance = distance;
        this.amount = amount;
        this.time = time;
    }
}

class Transfer{

    private Stations transfer_station;
    private Float transfer_amount;
    private Integer transfer_time;

    public Transfer(Stations transfer_station, Float transfer_amount, Integer transfer_time) {
        this.transfer_station = transfer_station;
        this.transfer_amount = transfer_amount;
        this.transfer_time = transfer_time;
    }
}