package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.PostApi;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.A_star;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Dijkstra;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Path_Calculate {

    private final Dijkstra dijkstra;
    private final FindNearestStation findNearestStation;
    private final Graph graph;
    private final A_star aStar;

    @Autowired
    public Path_Calculate(Dijkstra dijkstra,A_star aStar , FindNearestStation findNearestStation, Graph graph) {
        this.dijkstra = dijkstra;
        this.aStar = aStar;
        this.findNearestStation = findNearestStation;
        this.graph = graph;
    }

    public static void print2DArray(double[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print("data" + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private List<Map<String, Double>> convertPathToCoords(double[][] path) {
        return Arrays.stream(path)
                .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                .collect(Collectors.toList());
    }

    public HashMap<String, List<Route>> path_calculate(@RequestBody RequestData data){

        Stations startStation = findNearestStation.find_nearest_station(data.getCurrentLocation()).getFirst().stations();
        System.out.println("Start station: " + startStation.getStationID());
        Stations endStation = findNearestStation.find_nearest_station(data.getTargetLocation()).getFirst().stations();
        System.out.println("End station: " + endStation.getStationID());

        Path path_for_time = aStar.findShortestPaths(startStation, endStation, Metric.TIME);
        print2DArray(path_for_time.path());
        System.out.println("Time: " + path_for_time.time());

        Path path_for_distance = aStar.findShortestPaths(startStation, endStation, Metric.DISTANCE);
        print2DArray(path_for_distance.path());
        System.out.println("Distance: " + path_for_distance.distance());

        Path path_for_amount = aStar.findShortestPaths(startStation, endStation, Metric.AMOUNT);
        print2DArray(path_for_amount.path());
        System.out.println("Amount: " + path_for_amount.amount());

        List<Route> routes = List.of(
                new Route(convertPathToCoords(path_for_time.path()), path_for_time.distance(), path_for_time.time(), path_for_time.amount(), "Süre (Time)"),
                new Route(convertPathToCoords(path_for_distance.path()), path_for_distance.distance(), path_for_distance.time(), path_for_distance.amount(), "Mesafe (Distance)"),
                new Route(convertPathToCoords(path_for_amount.path()), path_for_amount.distance(), path_for_amount.time(), path_for_amount.amount(), "Tutar (Amount)")
        );

        HashMap <String, List<Route>> backEndReturn= new HashMap<>();
        backEndReturn.put("routes", routes);

        return backEndReturn;
    }

}
