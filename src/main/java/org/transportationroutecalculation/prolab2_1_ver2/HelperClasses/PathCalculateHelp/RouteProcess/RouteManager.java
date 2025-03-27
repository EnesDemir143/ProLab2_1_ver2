// RouteManager.java
package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathType;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler.AfterStation;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler.BeforeStation;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RouteManager {

    private final PathFactory pathFactory;
    private final BeforeStation beforeStation;
    private final AfterStation afterStation;
    private final AlternativePath alternativePath;

    @Autowired
    public RouteManager(PathFactory pathFactory, BeforeStation beforeStation, AfterStation afterStation, AlternativePath alternativePath) {
        this.pathFactory = pathFactory;
        this.beforeStation = beforeStation;
        this.afterStation = afterStation;
        this.alternativePath = alternativePath;
    }

    public HashMap<String, List<Route>> createRoutes(RequestData frontend_data, Stations startStation, Stations endStation, String type) {
        HashMap<String, List<Route>> backEndReturn = new HashMap<>();
        List<Path> paths = new ArrayList<>();
        List<Route> routes = new ArrayList<>();

        for (Metric metric : Metric.values()) {
            paths.add(pathFactory.createPath(startStation, endStation, metric, type));
        }

        for (Path path : paths) {
            if (path == null) {
                System.err.println("RouteManager: Path not found!");
                return null;
            }
            beforeStation.processStation(frontend_data, path);
            afterStation.processStation(frontend_data, path);

            routes.add(RouteBuilder.buildFromPath(path));
        }

        backEndReturn.put("routes", routes);

        return backEndReturn;
    }

    public Path AlternativePath(RequestData frontend_data, HashMap<String, List<Route>> routes) {
        return alternativePath.calculatePath(frontend_data);
    }

}