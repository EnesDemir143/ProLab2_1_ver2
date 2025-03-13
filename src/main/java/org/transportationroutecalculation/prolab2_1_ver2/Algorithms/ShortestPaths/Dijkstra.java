package org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Edge;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class Dijkstra implements ShortestPaths {

    private final Graph graph;

    @Autowired
    public Dijkstra(Graph graph) {
        this.graph = graph;
        // Graph'ın boş olup olmadığını kontrol et
        if (graph.getGraph().isEmpty()) {
            System.err.println("Dijkstra: Graph boş! Veriler yüklenmedi.");
        } else {
            System.out.println("Dijkstra: Graph başarıyla yüklendi, durak sayısı: " + graph.getGraph().size());
        }
    }

    @Override
    public List<Route> findShortestPaths(Stations startStation, Stations endStation) {
        Map<Stations, List<Edge>> graphMap = graph.getGraph();

        Map<Stations, List<Cost>> costs = new HashMap<>();
        Map<Stations, Map<Cost, List<Stations>>> paths = new HashMap<>();
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingDouble(s -> s.cost.distance + s.cost.duration + s.cost.price));
        System.out.println(graphMap.keySet());
        // Başlangıç durumu
        for (Stations station : graphMap.keySet()) {
            System.out.println("Station: " + station.getStationID() + " (" + station.getName() + ")");
            costs.put(station, new ArrayList<>());
            paths.put(station, new HashMap<>());
        }
        System.out.println(startStation);
        Cost initialCost = new Cost(0.0, 0.0, 0.0);
        List<Stations> initialPath = new ArrayList<>(Collections.singletonList(startStation));
        costs.get(startStation).add(initialCost);
        paths.get(startStation).put(initialCost, initialPath);
        pq.add(new State(startStation, initialCost, initialPath));

        while (!pq.isEmpty()) {
            State currentState = pq.poll();
            Stations current = currentState.station;
            Cost currentCost = currentState.cost;
            List<Stations> currentPath = currentState.pathSoFar;

            if (current.equals(endStation)) {
                continue; // Hedefe ulaşıldı, ama tüm Pareto-optimal yolları bul
            }

            List<Edge> neighbors = graphMap.getOrDefault(current, Collections.emptyList());
            for (Edge edge : neighbors) {
                Stations neighbor = edge.getDestination();
                Cost newCost = new Cost(
                        currentCost.distance + edge.getDistance(),
                        currentCost.duration + edge.getTime(),
                        currentCost.price + edge.getAmount()
                );
                List<Stations> newPath = new ArrayList<>(currentPath);
                newPath.add(neighbor);

                List<Cost> neighborCosts = costs.get(neighbor);
                boolean isDominated = false;
                for (Cost existingCost : neighborCosts) {
                    if (existingCost.dominates(newCost)) {
                        isDominated = true;
                        break;
                    }
                }
                if (isDominated) continue;

                neighborCosts.removeIf(c -> newCost.dominates(c));
                neighborCosts.add(newCost);
                paths.get(neighbor).put(newCost, newPath);

                pq.add(new State(neighbor, newCost, newPath));
            }
        }

        // Hedefin Pareto-optimal yollarını Route listesine çevir
        List<Route> routes = new ArrayList<>();
        List<Cost> targetCosts = costs.get(endStation);
        if (targetCosts.isEmpty()) {
            return routes; // Hedefe ulaşılamadı
        }


        for (Cost cost : targetCosts) {
            List<Stations> pathStations = paths.get(endStation).get(cost);
            List<Coordinate> path = pathStations.stream()
                    .map(s -> new Coordinate(s.getLocation().getY(), s.getLocation().getX()))
                    .collect(Collectors.toList());

            String bestFor = determineBestFor(cost, targetCosts);
            routes.add(new Route(cost.distance, cost.duration, cost.price, bestFor, path));
        }

        return routes;
    }

    private String determineBestFor(Cost cost, List<Cost> allCosts) {
        boolean isFastest = allCosts.stream().allMatch(c -> cost.duration <= c.duration);
        boolean isCheapest = allCosts.stream().allMatch(c -> cost.price <= c.price);
        boolean isShortest = allCosts.stream().allMatch(c -> cost.distance <= c.distance);

        if (isFastest && isCheapest && isShortest) return "best";
        if (isFastest) return "fastest";
        if (isCheapest) return "cheapest";
        if (isShortest) return "shortest";
        return "balanced";
    }
}