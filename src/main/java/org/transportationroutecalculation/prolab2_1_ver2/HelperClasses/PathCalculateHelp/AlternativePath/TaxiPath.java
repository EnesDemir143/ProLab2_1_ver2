package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.AlternativeTimeCalculate.TaxiTimeCalculator;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Qualifier("taxi")
public class TaxiPath extends AlternativePath {

    private final TaxiTimeCalculator taxiTimeCalculator;

    @Autowired
    public TaxiPath(@Qualifier("haversine") DistanceCalculate distanceCalculate, JsonLoadService jsonLoadService, TaxiTimeCalculator taxiTimeCalculator) {
        super(distanceCalculate, jsonLoadService);
        this.taxiTimeCalculator = taxiTimeCalculator;

    }

    @Override
    public Path2 calculatePath(@RequestBody RequestData frontend_data) {
        double distance = getDistanceCalculate().calculateDistance(frontend_data.getCurrentLocation().getLocation(), frontend_data.getTargetLocation().getLocation());
        Deque<Map.Entry<String, double[]>> path = new LinkedList<>();
        path.addFirst(new AbstractMap.SimpleEntry<>("taxi", new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()}));
        path.addLast(new AbstractMap.SimpleEntry<>("taxi", new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()}));

        Path2 paths = new Path2(path, distance, taxiTimeCalculator.calculateTime(distance), (getData().getTaxi().calculate_price(distance)), "Taksi (Taxi)");
        System.out.println(paths.getDistance());
        return paths;
    }

}
