package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.Metric;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.ShortestPaths;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

@Component
public class PathFactory {

    private final ShortestPaths shortestPaths;

    @Autowired
    public PathFactory(ShortestPaths shortestPaths) {
        this.shortestPaths = shortestPaths;
    }

    public Path2 createPath(Stations startStation, Stations endStation, Metric metric, String type) {
        return shortestPaths.findShortestPaths(startStation, endStation, metric, type);
    }
}