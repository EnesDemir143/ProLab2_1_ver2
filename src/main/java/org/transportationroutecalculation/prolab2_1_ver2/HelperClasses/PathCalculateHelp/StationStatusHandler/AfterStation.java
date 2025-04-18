package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathClasses.Path2;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingControllerService;

import java.awt.geom.Point2D;
import java.util.AbstractMap;

@Service
public class AfterStation implements StationHandler {

    private final WalkingControllerService walkingController;
    private final Data data;

    @Autowired
    public AfterStation(WalkingControllerService walkingController, JsonLoadService jsonLoadService) {
        this.walkingController = walkingController;
        this.data = jsonLoadService.getData();
    }

    @Override
    public void processStation(RequestData requestData, Path2 path) {
        last_station_to_end(requestData, path);
    }

    public void last_station_to_end(RequestData frontend_data, Path2 path){
        double[] lastPoint = path.getPath().getLast().getValue();
        Point2D.Double point_end = new Point2D.Double(lastPoint[0], lastPoint[1]);

        Boolean walk_control_end =(walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getKey();
        double distance_end = (walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getValue();

        if (walk_control_end){
            path.getPath().addLast(new AbstractMap.SimpleEntry<>("walk", new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()}));
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_end);
            path.getPath().addLast(new AbstractMap.SimpleEntry<>("taxi", new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()}));
            path.setDistance(path.getDistance() + distance_end);
            path.setAmount(path.getAmount() + taxi_price);
        }
    }
}