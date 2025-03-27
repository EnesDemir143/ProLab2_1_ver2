package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Metrics;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.ShortestPaths;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class A_star implements ShortestPaths {

    private final Graph graph;
    private final Map<Stations, List<Edge>> graphMap;
    private final AstarGraph astarGraph;

    @Autowired
    public A_star(Graph graph, AstarGraph astarGraph) {
        this.astarGraph = astarGraph;
        this.graph = graph;
        this.graphMap = this.graph.getGraph();
        if (graphMap.isEmpty()) {
            System.err.println("A_star: Graph boş! Veriler yüklenmedi.");
        } else {
            System.out.println("A_star: Graph başarıyla yüklendi, durak sayısı: " + graph.getGraph().size());
        }
    }

    private Metrics calculateAllMetrics(List<Node> path){
        double amount = 0;
        int time = 0;
        double distance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            for (Edge edge : graphMap.get(path.get(i).station)) {
                if (edge.getDestination().equals(path.get(i + 1).station)) {
                    amount += edge.getAmount();
                    time += edge.getTime();
                    distance += edge.getDistance();
                }
            }
        }

        return new Metrics(amount, time, distance);
    }


    private List<Node> followPath(Node node) {
        List<Node> path = new ArrayList<>();
        Node current = node;
        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }


    @Override
    public Path findShortestPaths(Stations startStation, Stations endStation, Metric metric) {

        Map<Node, List<Node>> graph_with_nodes = astarGraph.create_graph_with_nodes(endStation);
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        HashSet<Node> closedSet = new HashSet<>();

        Node startNode = graph_with_nodes.keySet().stream()
                .filter(n -> Objects.equals(n.station.getStationID(), startStation.getStationID()))
                .findFirst().orElse(null);
        if (startNode != null) {
            startNode.gcost = 0;
            startNode.fcost = startNode.hcost;
            openSet.add(startNode);
        }

        while (!openSet.isEmpty()) {

            Node current = openSet.poll();
            System.out.println("Current station: " + current.station.getName());

            if (current.station.equals(endStation)){
                Metrics calculatedMetrics = calculateAllMetrics(followPath(current));

                Deque<Map.Entry<String, double[]>> paths = new LinkedList<>();
                followPath(current)
                        .forEach(node ->
                                paths.add(Map.entry(node.station.getStationType(), new double[]{node.x, node.y}))
                        );
                return new Path(paths,new AtomicReference<>(calculatedMetrics.distance()),new AtomicReference<>(calculatedMetrics.time()),new AtomicReference<>(calculatedMetrics.amount()), (String) metric.getMetricName());
            }

            openSet.remove(current);
            closedSet.add(current);

            if (graph_with_nodes.get(current) == null) {
                continue;
            }

            for (Node neighbor : graph_with_nodes.get(current)){
                double tentative_gScore = current.gcost + graphMap.get(current.station).stream().filter(station -> station.getDestination().equals(neighbor.station)).findFirst().map(metric.getMetricFunction()).orElse(0.0);

                if (tentative_gScore < neighbor.gcost){
                    neighbor.parent = current;
                    neighbor.gcost = tentative_gScore;
                    neighbor.fcost = neighbor.gcost + neighbor.hcost;

                    if (!closedSet.contains(neighbor)){
                        openSet.add(neighbor);
                    }
                }

            }
        }

        return null;
    }

}
