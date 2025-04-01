package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses;

import java.util.Deque;
import java.util.Map;

public class Path2 {

    private Deque<Map.Entry<String, double[]>> path;
    private double distance;
    private int time;
    private double amount;
    private String best_for;
    private double remainMoney;

    public Path2(Deque<Map.Entry<String, double[]>> path, double distance, int time, double amount, String best_for) {
        this.path = path;
        this.distance = distance;
        this.time = time;
        this.amount = amount;
        this.best_for = best_for;
        this.remainMoney = -1;
    }

    public double getRemainMoney() {
        return remainMoney;
    }

    public void setRemainMoney(double remainMoney) {
        this.remainMoney = remainMoney;
    }

    public String getBest_for() {
        return best_for;
    }

    public void setBest_for(String best_for) {
        this.best_for = best_for;
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

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Deque<Map.Entry<String, double[]>> getPath() {
        return path;
    }

    public void setPath(Deque<Map.Entry<String, double[]>> path) {
        this.path = path;
    }
}
