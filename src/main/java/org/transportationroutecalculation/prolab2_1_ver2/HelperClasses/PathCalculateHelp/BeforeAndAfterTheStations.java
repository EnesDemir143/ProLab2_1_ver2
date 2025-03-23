package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.PathCalculateHelp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.transportationroutecalculation.prolab2_1_ver2.APİs.RequestData;
import org.transportationroutecalculation.prolab2_1_ver2.Algorithms.ShortestPaths.A_star.PathRecords.Path;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.Data;
import org.transportationroutecalculation.prolab2_1_ver2.DataLoad.JsonLoad;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers.WalkingController;

import java.awt.geom.Point2D;

@Service
public class BeforeAndAfterTheStations {

    private final WalkingController walkingController;
    private final Data data;
    private final JsonLoad jsonLoad;

    @Autowired
    public BeforeAndAfterTheStations(WalkingController walkingController, JsonLoad JsonLoad) {
        this.walkingController = walkingController;
        this.jsonLoad = JsonLoad;
        this.data = jsonLoad.getData();
    }


    public void last_station_to_end(@RequestBody RequestData frontend_data, Path path){

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

    public void start_to_end(@RequestBody RequestData frontend_data, Path path){

        double[] startPoint = path.path().getFirst();
        Point2D.Double point_start = new Point2D.Double(startPoint[0], startPoint[1]);

        Boolean walk_control_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getKey();
        double distance_start = (walkingController.can_proceed(frontend_data.getCurrentLocation().getLocation(), point_start)).getValue();
        System.out.println("Distance nearest: " + distance_start);


        if (walk_control_start){
            path.path().addFirst(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
        }
        else{
            double taxi_price = data.getTaxi().calculate_price(distance_start);

            path.path().addFirst(new double[]{frontend_data.getCurrentLocation().getLocation().getX(), frontend_data.getCurrentLocation().getLocation().getY()});
            path.distance().set(path.distance().get() + distance_start);
            path.amount().set(path.amount().get() + taxi_price);
        }
    }
}
