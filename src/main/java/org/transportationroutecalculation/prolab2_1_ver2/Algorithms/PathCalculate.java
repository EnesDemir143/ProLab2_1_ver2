package org.transportationroutecalculation.prolab2_1_ver2.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;

import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.FindNearestStation;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.RouteProcess.RouteConcat;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.*;


@Service
public class PathCalculate {

    private final FindNearestStation findNearestStation;
    private final RouteConcat routeConcat;

    @Autowired
    public PathCalculate(FindNearestStation findNearestStation, RouteConcat routeConcat) {
        this.findNearestStation = findNearestStation;
        this.routeConcat = routeConcat;
    }


    public HashMap<String, List<Route2>> path_calculate(@RequestBody RequestData frontend_data, String type){

        HashMap<String, List<Route2>> backEndReturn;
Stations startStation = findNearestStation.find_nearest_station(frontend_data.getCurrentLocation()).stream()
                .filter(x -> "notype".equals(type) || x.stations().getStationType().equals(type))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No suitable station found"))
                .stations();
        System.out.println("Start station: " + startStation.getStationID());

        Stations endStation = findNearestStation.find_nearest_station(frontend_data.getTargetLocation()).stream()
                .filter(x -> "notype".equals(type) || x.stations().getStationType().equals(type))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No suitable station found"))
                .stations();
        System.out.println("End station: " + endStation.getStationID());

        backEndReturn = routeConcat.route_concat(frontend_data, startStation, endStation, type);

        return backEndReturn;
    }

}
