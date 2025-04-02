package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;

import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FilterNearestStations;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteConcat;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;


@Service
public class PathCalculate {

    private final FindNearestStation findNearestStation;
    private final RouteConcat routeConcat;
    private final FilterNearestStations filterNearestStations;

    @Autowired
    public PathCalculate(FindNearestStation findNearestStation, RouteConcat routeConcat, FilterNearestStations filterNearestStations) {
        this.findNearestStation = findNearestStation;
        this.routeConcat = routeConcat;
        this.filterNearestStations = filterNearestStations;
    }


    public HashMap<String, List<Path2>> path_calculate(@RequestBody RequestData frontend_data, String type){
        HashMap<String, List<Path2>> backEndReturn;

        Stations startStation = filterNearestStations.filterStation(findNearestStation.find_nearest_station(frontend_data.getCurrentLocation()), type);
        System.out.println("Start station: " + startStation.getStationID());

        Stations endStation = filterNearestStations.filterStation(findNearestStation.find_nearest_station(frontend_data.getTargetLocation()), type);
        System.out.println("End station: " + endStation.getStationID());

        backEndReturn = routeConcat.route_concat(frontend_data, startStation, endStation, type);

        return backEndReturn;
    }

}
