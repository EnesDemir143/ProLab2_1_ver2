package org.transportationroutecalculation.prolab2_1_ver2.Graph;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.NextStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Transfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Graph {

    private Map<Stations, List<Edge>> graph = new HashMap<>();
    private final JsonLoad jsonLoad;
    private final Data data;

    @Autowired
    public Graph(JsonLoad jsonLoad) {
        this.jsonLoad = jsonLoad;
        this.data = jsonLoad.getData();
        System.out.println("Graph: Stations boyutu: " + (data.getStations() != null ? data.getStations().length : "null"));
    }

    @PostConstruct
    public void init() {
        createGraph();
    }

    public void createGraph() {
            for (Stations station : data.getStations()) {
            graph.putIfAbsent(station, new ArrayList<>());
            }

            for (Stations station : data.getStations()) {
                for (NextStation nextStation : station.getNext_stations()) {
                    addEdge(station, nextStation.getNextstation(), nextStation.getDistance(), nextStation.getAmount(), nextStation.getTime());
                    addEdge(nextStation.getNextstation(), station, nextStation.getDistance(), nextStation.getAmount(), nextStation.getTime());
                }
                for (Transfer transfer : station.getTransfer()) {
                    addEdge(station, transfer.getTransferStation(), transfer.getTransfer_distance(), transfer.getTransfer_amount(), transfer.getTransfer_time());
                    addEdge(transfer.getTransferStation(), station, transfer.getTransfer_distance(), transfer.getTransfer_amount(), transfer.getTransfer_time());
                }
            }

        printGraph();
    }

    public void addEdge(Stations node, Stations destination, double distance, double amount, int time) {
        System.out.println(node.getStationID());
        graph.putIfAbsent(node, new ArrayList<>());
        graph.get(node).add(new Edge(destination, distance, amount, time));
    }

    public Map<Stations, List<Edge>> getGraph() {
        return graph;
    }

    private void printGraph() {
        System.out.println("Graphsize : " + graph.keySet().size());
        for (Map.Entry<Stations, List<Edge>> entry : graph.entrySet()) {
            Stations station = entry.getKey();
            List<Edge> edges = entry.getValue();
            System.out.println("Station: " + station.getStationID() + " (" + station.getName() + ")");
            for (Edge edge : edges) {
                System.out.println("  -> Next Station: " + edge.getDestination().getStationID() + " (" + edge.getDestination().getName() + "), Distance: " + edge.getDistance() + ", Amount: " + edge.getAmount() + ", Time: " + edge.getTime());
            }
        }
    }
}