package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import java.util.Deque;
import java.util.Map;

public class Route2 {

    private Deque<Map.Entry<String, double[]>> path;
    private double distance;
    private int time;
    private double amount;
    private String bestFor;

    public Route2(Deque<Map.Entry<String, double[]>> path, double distance, int time, double amount, String bestFor) {
        this.path = path;
        this.distance = distance;
        this.time = time;
        this.amount = amount;
        this.bestFor = bestFor;
    }

    public Deque<Map.Entry<String, double[]>> getPath() {
        return path;
    }

    public void setPath(Deque<Map.Entry<String, double[]>> path) {
        this.path = path;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBestFor() {
        return bestFor;
    }

    public void setBestFor(String bestFor) {
        this.bestFor = bestFor;
    }
}
