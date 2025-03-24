package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.TaxiTime;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TaxiPath {

    private final DistanceCalculate distanceCalculate;
    private final Data data;
    private final JsonLoad jsonLoad;

    @Autowired
    public TaxiPath(@Qualifier("haversine") DistanceCalculate distanceCalculate, JsonLoad JsonLoad) {
        this.distanceCalculate = distanceCalculate;
        this.jsonLoad = JsonLoad;
        this.data = jsonLoad.getData();
    }

    public Path justTaxi(@RequestBody RequestData frontend_data) {


        double distance = distanceCalculate.calculateDistance(frontend_data.getCurrentLocation().getLocation(), frontend_data.getTargetLocation().getLocation());
        ArrayList<double[]> path = new ArrayList<>();
        path.add(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
        path.add(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});

        Path paths = new Path(path, new AtomicReference<>(distance), new AtomicReference<>(new TaxiTime().calculateTime(distance)), new AtomicReference<>(data.getTaxi().calculate_price(distance)), "Taksi (Taxi)");

        return paths;
    }

}
