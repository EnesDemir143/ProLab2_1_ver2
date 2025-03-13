package org.transportationroutecalculation.prolab2_1_ver2.Graph;

import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Graph {

    private Map<Stations, List<Edge>> graph = new HashMap<>();

    public void addEdge(Stations node,Stations destination, double distance, double amount, int time) {
        graph.putIfAbsent(node, new ArrayList<>());
        graph.get(node).add(new Edge(destination, distance, amount, time));
    }

    public Map<Stations, List<Edge>> getGraph() {

        return graph;
    }
}
