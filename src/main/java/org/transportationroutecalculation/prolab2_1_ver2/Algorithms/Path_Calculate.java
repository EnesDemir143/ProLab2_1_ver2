package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.A_star;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Dijkstra;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.Graph.Graph;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingController;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Path_Calculate {

    private final Dijkstra dijkstra;
    private final FindNearestStation findNearestStation;
    private final Graph graph;
    private final A_star aStar;
    private final WalkingController walkingController;
    private final Data data;
    private final JsonLoad jsonLoad;

    @Autowired
    public Path_Calculate(JsonLoad JsonLoad, WalkingController walkingController, Dijkstra dijkstra, A_star aStar , FindNearestStation findNearestStation, Graph graph, JsonLoad jsonLoad) {
        this.jsonLoad = JsonLoad;
        this.data = jsonLoad.getData();
        this.walkingController = walkingController;
        this.dijkstra = dijkstra;
        this.aStar = aStar;
        this.findNearestStation = findNearestStation;
        this.graph = graph;
    }



    public static List<Map<String, Double>> convertPathToCoords(List<double[]> path) {
        return path.stream()
                .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                .collect(Collectors.toList());
    }

    public HashMap<String, List<Route>> path_calculate(@RequestBody RequestData frontend_data){

        Stations startStation = findNearestStation.find_nearest_station(frontend_data.getCurrentLocation()).getFirst().stations();
        System.out.println("Start station: " + startStation.getStationID());
        Stations endStation = findNearestStation.find_nearest_station(frontend_data.getTargetLocation()).getFirst().stations();
        System.out.println("End station: " + endStation.getStationID());

        HashMap <String, List<Route>> backEndReturn= new HashMap<>();

        Boolean walk_control =(walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(),startStation.getLocation())).getKey();
        double distance = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(),startStation.getLocation())).getValue();

        double[] firststation = {frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()};
        double[] laststation = {frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()};

        if (walk_control){
            Path path_for_time = aStar.findShortestPaths(startStation, endStation, Metric.TIME);
            path_for_time.path().addFirst(firststation);
            path_for_time.path().addLast(laststation);
            System.out.println("Time: " + path_for_time.time());

            Path path_for_distance = aStar.findShortestPaths(startStation, endStation, Metric.DISTANCE);
            path_for_distance.path().addFirst(firststation);
            path_for_distance.path().addLast(laststation);
            System.out.println("Distance: " + path_for_distance.distance());

            Path path_for_amount = aStar.findShortestPaths(startStation, endStation, Metric.AMOUNT);
            path_for_amount.path().addFirst(firststation);
            path_for_amount.path().addLast(laststation);
            System.out.println("Amount: " + path_for_amount.amount());

            List<Route> routes = List.of(
                    new Route(convertPathToCoords(path_for_time.path()), path_for_time.distance().get(), path_for_time.time().get(), path_for_time.amount().get(), "Süre (Time)"),
                    new Route(convertPathToCoords(path_for_distance.path()), path_for_distance.distance().get(), path_for_distance.time().get(), path_for_distance.amount().get(), "Mesafe (Distance)"),
                    new Route(convertPathToCoords(path_for_amount.path()), path_for_amount.distance().get(), path_for_amount.time().get(), path_for_amount.amount().get(), "Tutar (Amount)")
            );

            backEndReturn.put("routes", routes);
        }

        else{
            double taxi_price = data.getTaxi().calculate_price(distance);

            Path path_for_time = aStar.findShortestPaths(startStation, endStation, Metric.TIME);
            path_for_time.path().addFirst(firststation);
            path_for_time.path().addLast(laststation);
            System.out.println("Time: " + path_for_time.time());

            Path path_for_distance = aStar.findShortestPaths(startStation, endStation, Metric.DISTANCE);
            path_for_distance.path().addFirst(firststation);
            path_for_distance.path().addLast(laststation);
            System.out.println("Distance: " + path_for_distance.distance());

            Path path_for_amount = aStar.findShortestPaths(startStation, endStation, Metric.AMOUNT);
            path_for_amount.path().addFirst(firststation);
            path_for_amount.path().addLast(laststation);
            System.out.println("Amount: " + path_for_amount.amount());

        }



        return backEndReturn;
    }

}
