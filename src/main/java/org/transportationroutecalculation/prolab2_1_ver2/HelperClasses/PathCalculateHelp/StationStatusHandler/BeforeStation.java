package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingControllerService;

import java.awt.geom.Point2D;
import java.util.AbstractMap;

@Service
public class BeforeStation implements StationHandler {

    private final WalkingControllerService walkingController;
    private final Data data;

    @Autowired
    public BeforeStation(WalkingControllerService walkingController, JsonLoadService jsonLoadService) {
        this.walkingController = walkingController;
        this.data = jsonLoadService.getData();
    }

    @Override
    public void processStation(RequestData requestData, Path path) {
        start_to_end(requestData, path);
    }

    public void start_to_end(RequestData frontend_data, Path path){
        double[] startPoint = path.path().getFirst().getValue();
        Point2D.Double point_start = new Point2D.Double(startPoint[0], startPoint[1]);

        Boolean walk_control_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getKey();
        double distance_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getValue();

        if (walk_control_start){
            path.path().addFirst(new AbstractMap.SimpleEntry<>("walk", new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()}));
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_start);

            path.path().addFirst(new AbstractMap.SimpleEntry<>("taxi", new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()}));
            path.distance().set(path.distance().get() + distance_start);
            path.amount().set(path.amount().get() + taxi_price);
        }
    }
}