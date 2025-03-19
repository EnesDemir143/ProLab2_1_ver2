package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.A_star;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.Dijkstra;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.ShortestPaths;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;

import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingController;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.awt.geom.Point2D;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class Path_Calculate {

    private final FindNearestStation findNearestStation;
    private final ShortestPaths shortestPaths;
    private final WalkingController walkingController;
    private final Data data;
    private final JsonLoad jsonLoad;
    private final DistanceCalculate distanceCalculate;

    @Autowired
    public Path_Calculate(JsonLoad JsonLoad, WalkingController walkingController, @Qualifier("a_star") ShortestPaths shortestPaths, FindNearestStation findNearestStation ,JsonLoad jsonLoad, @Qualifier("haversine") DistanceCalculate distanceCalculate) {
        this.jsonLoad = JsonLoad;
        this.data = jsonLoad.getData();
        this.walkingController = walkingController;
        this.shortestPaths = shortestPaths;
        this.findNearestStation = findNearestStation;
        this.distanceCalculate = distanceCalculate;
    }



    public static List<Map<String, Double>> convertPathToCoords(List<double[]> path) {
        return path.stream()
                .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                .collect(Collectors.toList());
    }

    public void last_station_to_end(@RequestBody RequestData frontend_data, Path path){

        double[] lastPoint = path.path().getLast();
        Point2D.Double point_end = new Point2D.Double(lastPoint[0], lastPoint[1]);

        Boolean walk_control_end =(walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getKey();
        double distance_end = (walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getValue();

        if (walk_control_end){
            path.path().addLast(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_end);
            path.path().addLast(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});
            path.distance().set(path.distance().get() + distance_end);
            path.amount().set(path.amount().get() + taxi_price);
        }

    }

    public void start_to_end(@RequestBody RequestData frontend_data, Path path){

        double[] startPoint = path.path().getFirst();
        Point2D.Double point_start = new Point2D.Double(startPoint[0], startPoint[1]);

        Boolean walk_control_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getKey();
        double distance_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getValue();
        System.out.println("Distance nearest: " + distance_start);


        if (walk_control_start){
            path.path().addFirst(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_start);

            path.path().addFirst(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
            path.distance().set(path.distance().get() + distance_start);
            path.amount().set(path.amount().get() + taxi_price);
        }
    }

    public HashMap<String, List<Route>> route_concat(@RequestBody RequestData frontend_data, Stations startStation, Stations endStation){

        HashMap <String, List<Route>> backEndReturn= new HashMap<>();

        Path path_for_time = shortestPaths.findShortestPaths(startStation, endStation, Metric.TIME);
        System.out.println("Time: " + path_for_time.time());
        start_to_end(frontend_data, path_for_time);
        last_station_to_end(frontend_data, path_for_time);

        Path path_for_distance = shortestPaths.findShortestPaths(startStation, endStation, Metric.DISTANCE);
        System.out.println("Distance: " + path_for_distance.distance());

        start_to_end(frontend_data, path_for_distance);
        last_station_to_end(frontend_data, path_for_distance);

        Path path_for_amount = shortestPaths.findShortestPaths(startStation, endStation, Metric.AMOUNT);
        System.out.println("Amount: " + path_for_amount.amount());
        start_to_end(frontend_data, path_for_amount);
        last_station_to_end(frontend_data, path_for_amount);

        Path taxi_path = justTaxi(frontend_data);

        List<Route> routes = List.of(
                new Route(convertPathToCoords(path_for_time.path()), new AtomicReference<>(path_for_time.distance().get()), new AtomicReference<>(path_for_time.time().get()), new AtomicReference<>(path_for_time.amount().get()), path_for_time.best_for()),
                new Route(convertPathToCoords(path_for_distance.path()), new AtomicReference<>(path_for_distance.distance().get()), new AtomicReference<>(path_for_distance.time().get()), new AtomicReference<>(path_for_distance.amount().get()), path_for_distance.best_for()),
                new Route(convertPathToCoords(path_for_amount.path()), new AtomicReference<>(path_for_amount.distance().get()), new AtomicReference<>(path_for_amount.time().get()), new AtomicReference<>(path_for_amount.amount().get()), path_for_time.best_for()),
                new Route(convertPathToCoords(taxi_path.path()), new AtomicReference<>(taxi_path.distance().get()), new AtomicReference<>(taxi_path.time().get()), new AtomicReference<>(taxi_path.amount().get()), taxi_path.best_for())
        );

        backEndReturn.put("routes", routes);

        return backEndReturn;
    }

    public Path justTaxi(@RequestBody RequestData frontend_data) {


        double distance = distanceCalculate.calculateDistance(frontend_data.getCurrentLocation().getLocation(), frontend_data.getTargetLocation().getLocation());
        ArrayList<double[]> path = new ArrayList<>();
        path.add(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
        path.add(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});

        Path paths = new Path(path, new AtomicReference<>(distance), new AtomicReference<>(0), new AtomicReference<>(data.getTaxi().calculate_price(distance)), "Taksi (Taxi)");

        return paths;
    }

    public HashMap<String, List<Route>> path_calculate(@RequestBody RequestData frontend_data){

        HashMap<String, List<Route>> backEndReturn;

        Stations startStation = findNearestStation.find_nearest_station(frontend_data.getCurrentLocation()).getFirst().stations();
        System.out.println("Start station: " + startStation.getStationID());
        Stations endStation = findNearestStation.find_nearest_station(frontend_data.getTargetLocation()).getFirst().stations();
        System.out.println("End station: " + endStation.getStationID());

        backEndReturn = route_concat(frontend_data, startStation, endStation);

        return backEndReturn;
    }

}
