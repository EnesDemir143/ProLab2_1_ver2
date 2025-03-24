package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Qualifier("taxi")
public class TaxiPath extends AlternativePath{

    @Autowired
    public TaxiPath(@Qualifier("haversine") DistanceCalculate distanceCalculate, JsonLoad JsonLoad) {
        super(distanceCalculate, JsonLoad);
    }

    @Override
    public Path calculatePath(@RequestBody RequestData frontend_data) {
        double distance = getDistanceCalculate().calculateDistance(frontend_data.getCurrentLocation().getLocation(), frontend_data.getTargetLocation().getLocation());
        ArrayList<double[]> path = new ArrayList<>();
        path.add(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
        path.add(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});

        Path paths = new Path(path, new AtomicReference<>(distance), new AtomicReference<>(new TaxiTime().calculateTime(distance)), new AtomicReference<>(getData().getTaxi().calculate_price(distance)), "Taksi (Taxi)");
        System.out.println(paths.distance().get());
        return paths;
    }


}
