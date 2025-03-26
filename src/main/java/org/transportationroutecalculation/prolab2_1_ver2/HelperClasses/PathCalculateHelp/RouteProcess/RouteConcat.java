package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.Route;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.HashMap;
import java.util.List;

@Service
public class RouteConcat {

    private final RouteManager routeManager;

    @Autowired
    public RouteConcat(RouteManager routeManager) {
        this.routeManager = routeManager;
    }

    public HashMap<String, List<Route>> route_concat(RequestData frontend_data, Stations startStation, Stations endStation) {
        return routeManager.createRoutes(frontend_data, startStation, endStation);
    }
}