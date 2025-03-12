package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.Locations;
import org.transportationroutecalculation.prolab2_1_ver2.MainClasses.StationTypes.Stations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FindNearestStation {

    private final Data data;
    private final DistanceCalculate distanceCalculate;

    @Autowired
    public FindNearestStation (JsonLoad jsonLoad,@Qualifier("euclidean") DistanceCalculate distanceCalculate) {
        this.data = jsonLoad.getData();
        this.distanceCalculate = distanceCalculate;
    }

    public List<Distances<String, Double>> find_nearest_station(Locations location) {

        List<Distances<String, Double>> distanceList = new ArrayList<>();

        for (Stations station : data.getStations()){
            double distance = distanceCalculate.calculateDistance(location.getLocation(), station.getLocation());
            distanceList.add(new Distances<>(station.getStationID(), distance));
        }

        distanceList.sort((a, b) -> a.distance().compareTo(b.distance()));

        return distanceList;
    }
}
