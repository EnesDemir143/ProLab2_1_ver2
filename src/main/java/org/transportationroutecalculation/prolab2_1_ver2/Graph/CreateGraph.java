package org.transportationroutecalculation.prolab2_1_ver2.Graph;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.NextStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.List;
import java.util.Map;

@Service
public class CreateGraph {

    private JsonLoad jsonLoad;
    private Data data;

    @Autowired
    public CreateGraph(JsonLoad jsonLoad) {
        this.data = jsonLoad.getData();
    }

    @PostConstruct
    public void init() {
        CreateGraph();
    }

    public Map<Stations, List<Edge>> CreateGraph() {

        Graph graph = new Graph();

        for (Stations station : data.getStations()){

            for (NextStation destination : station.getNext_stations()){

                graph.addEdge(station, destination.getNextstation(), destination.getDistance(), destination.getAmount(), destination.getTime());

            }

        }

        Map<Stations, List<Edge>> graphMap = graph.getGraph();
        printGraph(graphMap);
        return graph.getGraph();

    }
public void printGraph(Map<Stations, List<Edge>> graph) {
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
