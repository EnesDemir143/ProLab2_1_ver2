package org.transportationroutecalculation.prolab2_1_ver2.Graph;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Edge {

    private Stations destination;
    double distance;
    double amount;
    int time;

    public Edge(Stations destination, double distance, double amount, int time) {
        this.destination = destination;
        this.distance = distance;
        this.amount = amount;
        this.time = time;
    }

}


public class Graph {

    private Map<Stations, List<Edge>> graph = new HashMap<>();



}
