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

}