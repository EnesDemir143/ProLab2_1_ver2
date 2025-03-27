package org.transportationroutecalculation.prolab2_1_ver2.Graph;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

public class Edge {

    private Stations destination;
    private double distance;
    private double amount;
    private int time;
    private String type;

    public Edge(Stations destination, double distance, double amount, int time, String type) {
        this.destination = destination;
        this.distance = distance;
        this.amount = amount;
        this.time = time;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stations getDestination() {
        return destination;
    }

    public void setDestination(Stations destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
