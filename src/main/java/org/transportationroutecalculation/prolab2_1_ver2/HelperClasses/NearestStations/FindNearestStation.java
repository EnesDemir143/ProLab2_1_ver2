package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations.Locations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FindNearestStation {

    private final Data data;
    private final DistanceCalculate distanceCalculate;

    @Autowired
    public FindNearestStation (JsonLoad jsonLoad,@Qualifier("haversine") DistanceCalculate distanceCalculate) {
        this.data = jsonLoad.getData();
        this.distanceCalculate = distanceCalculate;
    }

    public List<Distances<Stations, Double>> find_nearest_station(Locations location) {

        List<Distances<Stations, Double>> distanceList = new ArrayList<>();

        for (Stations station : data.getStations()){
            double distance = distanceCalculate.calculateDistance(location.getLocation(), station.getLocation());

            if (distance < 0){
                throw new RuntimeException("Distance can not be negative");
            }

            distanceList.add(new Distances<>(station, distance));
        }

        distanceList.sort((a, b) -> a.distance().compareTo(b.distance()));

        return distanceList;
    }
}
