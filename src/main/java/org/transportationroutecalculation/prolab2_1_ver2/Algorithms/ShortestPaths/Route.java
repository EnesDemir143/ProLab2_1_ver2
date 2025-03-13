package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;

class Cost {
    double distance;
    double duration;
    double price;

    public Cost(double distance, double duration, double price) {
        this.distance = distance;
        this.duration = duration;
        this.price = price;
    }

    public boolean dominates(Cost other) {
        return this.distance <= other.distance && this.duration <= other.duration &&
                this.price <= other.price &&
                (this.distance < other.distance || this.duration < other.duration || this.price < other.price);
    }
}

public class Route {
    public double distance;
    public double duration;
    public double price;
    public String bestFor;
    public List<Coordinate> path;

    public Route(double distance, double duration, double price, String bestFor, List<Coordinate> path) {
        this.distance = distance;
        this.duration = duration;
        this.price = price;
        this.bestFor = bestFor;
        this.path = path;
    }
}

class Coordinate {
    public double lat;
    public double lon;

    public Coordinate(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}

class State {
    Stations station;
    Cost cost;
    List<Stations> pathSoFar;

    State(Stations station, Cost cost, List<Stations> pathSoFar) {
        this.station = station;
        this.cost = cost;
        this.pathSoFar = pathSoFar;
    }
}