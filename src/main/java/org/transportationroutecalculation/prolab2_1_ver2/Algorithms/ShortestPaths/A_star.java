package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;

@Service
public class A_star implements ShortestPaths{

    private final Graph graph;
    private final Map<Stations, List<Edge>> graphMap;
    private static Map<Node, List<Node>> graph_with_nodes = new HashMap<>();

    @Autowired
    public A_star(Graph graph) {
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

    private List<Node> followPath(Node node){
        List<Node> path = new ArrayList<>();
        Node current = node;
        while (current !=null){
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private void create_graph_with_nodes(){

        for (Map.Entry<Stations, List<Edge>> entry : graphMap.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getKey().getLocation().getX(), entry.getKey().getLocation().getY());
            graph_with_nodes.putIfAbsent(node, new ArrayList<>());

            for(Edge edge : entry.getValue()){
                Node neighbor = new Node(edge.getDestination(), edge.getDestination().getLocation().getX(), edge.getDestination().getLocation().getY());
                neighbor.gcost = Double.POSITIVE_INFINITY;
                neighbor.hcost = calculateHeuristic(neighbor.x, neighbor.y, node.x, node.y);
                neighbor.fcost = Double.POSITIVE_INFINITY;
                neighbor.parent = node;
                graph_with_nodes.get(node).add(neighbor);

            }
        }
    }


    @Override
    public List<Node> findShortestPaths(Stations startStation, Stations endStation) {

        create_graph_with_nodes();
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        HashSet<Node> closedSet = new HashSet<>();

        for (Node graph_node : graph_with_nodes.keySet()){
            if (graph_node.station.getStationID() == startStation.getStationID()) {
                graph_node.gcost = 0;
                graph_node.fcost = graph_node.hcost;
                openSet.add(graph_node);
            }
        }

        while (!openSet.isEmpty()) {

            Node current = openSet.poll();

            if (current.x == endStation.getLocation().getX() && current.y == endStation.getLocation().getY()){
                return followPath(current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Node neighbor : graph_with_nodes.get(current)){
                double tentative_gScore = current.gcost + graphMap.get(current.station).stream().filter(station -> station.getDestination().equals(neighbor.station)).findFirst().get().getDistance();

                if (tentative_gScore < neighbor.gcost){
                    neighbor.parent = current;
                    neighbor.gcost = tentative_gScore;
                    neighbor.fcost = neighbor.gcost + neighbor.hcost;

                    if (!openSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }

            }
        }


        return null;
    }
}
