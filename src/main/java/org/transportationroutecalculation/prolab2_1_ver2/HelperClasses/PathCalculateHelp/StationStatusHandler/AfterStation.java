package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp.StationStatusHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoadService;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingControllerService;

import java.awt.geom.Point2D;

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
    public void processStation(RequestData requestData, Path path) {
        last_station_to_end(requestData, path);
    }

    public void last_station_to_end(RequestData frontend_data, Path path){
        double[] lastPoint = path.path().getLast();
        Point2D.Double point_end = new Point2D.Double(lastPoint[0], lastPoint[1]);

        Boolean walk_control_end =(walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getKey();
        double distance_end = (walkingController.can_proceed(point_end, frontend_data.getTargetLocation().getLocation())).getValue();

        if (walk_control_end){
            path.path().addLast(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_end);
            path.path().addLast(new double[]{frontend_data.getTargetLocation().getLocation().getX(), frontend_data.getTargetLocation().getLocation().getY()});
            path.distance().set(path.distance().get() + distance_end);
            path.amount().set(path.amount().get() + taxi_price);
        }
    }
}