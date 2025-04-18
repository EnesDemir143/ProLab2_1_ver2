package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath.AlternativePath;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.HashMap;
import java.util.List;

@Service
public class RouteConcat {

    private final RouteManager routeManager;
    private final AlternativePath alternativePath;

    @Autowired
    public RouteConcat(RouteManager routeManager, AlternativePath alternativePath) {
        this.routeManager = routeManager;
        this.alternativePath = alternativePath;
    }

    public HashMap<String, List<Path2>> route_concat(RequestData frontend_data, Stations startStation, Stations endStation, String type) {
        return routeManager.createRoutes(frontend_data, startStation, endStation, type);
    }
}