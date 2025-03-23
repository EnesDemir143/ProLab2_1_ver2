package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.ShortestPaths;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class RouteConcat {

    private final ShortestPaths shortestPaths;
    private final TaxiPath taxiPath;
    private final BeforeAndAfterTheStations beforeAndAfterTheStations;

    @Autowired
    public RouteConcat(TaxiPath taxiPath, @Qualifier("a_star") ShortestPaths shortestPaths, BeforeAndAfterTheStations beforeAndAfterTheStations) {
        this.shortestPaths = shortestPaths;
        this.taxiPath = taxiPath;
        this.beforeAndAfterTheStations = beforeAndAfterTheStations;
    }

    public static List<Map<String, Double>> convertPathToCoords(List<double[]> path) {
        return path.stream()
                .map(coord -> Map.of("lat", coord[0], "lon", coord[1]))
                .collect(Collectors.toList());
    }

    public HashMap<String, List<Route>> route_concat(@RequestBody RequestData frontend_data, Stations startStation, Stations endStation){

        HashMap <String, List<Route>> backEndReturn= new HashMap<>();

        Path path_for_time = shortestPaths.findShortestPaths(startStation, endStation, Metric.TIME);
        System.out.println("Time: " + path_for_time.time());
        beforeAndAfterTheStations.start_to_end(frontend_data, path_for_time);
        beforeAndAfterTheStations.last_station_to_end(frontend_data, path_for_time);

        Path path_for_distance = shortestPaths.findShortestPaths(startStation, endStation, Metric.DISTANCE);
        System.out.println("Distance: " + path_for_distance.distance());

        beforeAndAfterTheStations.start_to_end(frontend_data, path_for_distance);
        beforeAndAfterTheStations.last_station_to_end(frontend_data, path_for_distance);

        Path path_for_amount = shortestPaths.findShortestPaths(startStation, endStation, Metric.AMOUNT);
        System.out.println("Amount: " + path_for_amount.amount());
        beforeAndAfterTheStations.start_to_end(frontend_data, path_for_amount);
        beforeAndAfterTheStations.last_station_to_end(frontend_data, path_for_amount);

        Path taxi_path = taxiPath.justTaxi(frontend_data);

        List<Route> routes = List.of(
                new Route(convertPathToCoords(path_for_time.path()), new AtomicReference<>(path_for_time.distance().get()), new AtomicReference<>(path_for_time.time().get()), new AtomicReference<>(path_for_time.amount().get()), path_for_time.best_for()),
                new Route(convertPathToCoords(path_for_distance.path()), new AtomicReference<>(path_for_distance.distance().get()), new AtomicReference<>(path_for_distance.time().get()), new AtomicReference<>(path_for_distance.amount().get()), path_for_distance.best_for()),
                new Route(convertPathToCoords(path_for_amount.path()), new AtomicReference<>(path_for_amount.distance().get()), new AtomicReference<>(path_for_amount.time().get()), new AtomicReference<>(path_for_amount.amount().get()), path_for_time.best_for()),
                new Route(convertPathToCoords(taxi_path.path()), new AtomicReference<>(taxi_path.distance().get()), new AtomicReference<>(taxi_path.time().get()), new AtomicReference<>(taxi_path.amount().get()), taxi_path.best_for())
        );

        backEndReturn.put("routes", routes);

        return backEndReturn;
    }


}
