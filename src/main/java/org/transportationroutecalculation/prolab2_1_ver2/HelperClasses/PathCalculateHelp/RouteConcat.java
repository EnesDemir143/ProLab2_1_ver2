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

import java.util.ArrayList;
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

        List<Path > paths = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        Path path_for_time = shortestPaths.findShortestPaths(startStation, endStation, Metric.TIME);
        paths.add(path_for_time);
        Path path_for_distance = shortestPaths.findShortestPaths(startStation, endStation, Metric.DISTANCE);
        paths.add(path_for_distance);
        Path path_for_amount = shortestPaths.findShortestPaths(startStation, endStation, Metric.AMOUNT);
        paths.add(path_for_amount);

        for (Path path : paths) {
            if (path == null) {
                System.err.println("RouteConcat: Path bulunamadı!");
                return null;
            }
            System.out.println("Distance: " + path.distance());
            System.out.println("Time: " + path.time());
            System.out.println("Amount: " + path.amount());
            beforeAndAfterTheStations.start_to_end(frontend_data, path);
            beforeAndAfterTheStations.last_station_to_end(frontend_data, path);

            routes.add(new Route(convertPathToCoords(path.path()), new AtomicReference<>(path.distance().get()), new AtomicReference<>(path.time().get()), new AtomicReference<>(path.amount().get()), path.best_for()));
        }

        Path taxi_path = taxiPath.justTaxi(frontend_data);
        routes.add(new Route(convertPathToCoords(taxi_path.path()), new AtomicReference<>(taxi_path.distance().get()), new AtomicReference<>(taxi_path.time().get()), new AtomicReference<>(taxi_path.amount().get()), taxi_path.best_for()));

        backEndReturn.put("routes", routes);

        return backEndReturn;
    }


}
