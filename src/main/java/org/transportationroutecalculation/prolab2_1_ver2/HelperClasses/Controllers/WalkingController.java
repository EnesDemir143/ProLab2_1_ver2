package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;

import java.awt.geom.Point2D;
import java.util.Map;

@Service
class WalkingController extends Controllers {
    private static final Double MAX_WALKING_DISTANCE = 3.0;


/*    public Map.Entry<Boolean, Double> can_proceed(Point2D.Double source, Point2D.Double destination) {
        Double distance = distanceCalculator.calculateDistance(source, destination);

        if (distance < 0) {
            throw new IllegalArgumentException("Mesafa negatif olamaz");
        }

        return Map.entry(distance<=MAX_WALKING_DISTANCE, distance);
    }*/
}