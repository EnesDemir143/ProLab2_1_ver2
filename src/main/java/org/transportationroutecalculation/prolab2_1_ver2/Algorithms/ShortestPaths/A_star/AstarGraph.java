package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AstarGraph {

    private final Graph graph;
    private final Map<Stations, List<Edge>> graphMap;
    private static Map<Node, List<Node>> graph_with_nodes = new HashMap<>();

    @Autowired
    public AstarGraph(Graph graph) {
        this.graph = graph;
        this.graphMap = this.graph.getGraph();
        if (graphMap.isEmpty()) {
            System.err.println("A_star: Graph boş! Veriler yüklenmedi.");
        } else {
            System.out.println("A_star: Graph başarıyla yüklendi, durak sayısı: " + graph.getGraph().size());
        }
    }

    private double calculateHeuristic(double x1, double y1, double x2, double y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public Map<Node, List<Node>> create_graph_with_nodes(Stations endStation) {

        Map<Stations, Node> stationToNode = new HashMap<>();
        graph_with_nodes.clear();

        for (Stations station : graphMap.keySet()) {
            Node node = new Node(station, station.getLocation().getX(), station.getLocation().getY());
            node.gcost = Double.POSITIVE_INFINITY;
            node.hcost = calculateHeuristic(node.x, node.y, endStation.getLocation().getX(), endStation.getLocation().getY());
            node.fcost = Double.POSITIVE_INFINITY;
            stationToNode.put(station, node);
            graph_with_nodes.put(node, new ArrayList<>());
        }

        for (Map.Entry<Stations, List<Edge>> entry : graphMap.entrySet()) {
            Node node = stationToNode.get(entry.getKey());
            for (Edge edge : entry.getValue()) {
                Node neighbor = stationToNode.get(edge.getDestination());
                graph_with_nodes.get(node).add(neighbor);
            }
        }

        return graph_with_nodes;
    }
}
