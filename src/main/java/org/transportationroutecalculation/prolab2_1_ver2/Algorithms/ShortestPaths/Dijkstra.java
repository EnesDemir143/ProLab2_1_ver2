package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;


@Service
public class Dijkstra implements ShortestPaths {

    private final Graph graph;
    private final Map<Stations, List<Edge>> graphMap;

    @Autowired
    public Dijkstra(Graph graph) {
        this.graph = graph;
        this.graphMap = this.graph.getGraph();
        if (graph.getGraph().isEmpty()) {
            System.err.println("Dijkstra: Graph boş! Veriler yüklenmedi.");
        } else {
            System.out.println("Dijkstra: Graph başarıyla yüklendi, durak sayısı: " + graph.getGraph().size());
        }
    }

    @Override
    public List<Node> findShortestPaths(Stations startStation, Stations endStation) {


        return null;
    }
}