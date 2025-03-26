package org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.Controllers;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Service;
 import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.DistanceCalculate.DistanceCalculate;
 import org.transportationroutecalculation.prolab2_1_ver2.HelperClasses.NearestStations.Distances;

 import java.awt.geom.Point2D;
 import java.util.AbstractMap;
 import java.util.Map;

 @Service
 public class WalkingController extends Controllers implements WalkingControllerService {
     private static final Double MAX_WALKING_DISTANCE = 3.0;

     private DistanceCalculate distanceCalculate;

     @Autowired
     public WalkingController(@Qualifier("haversine") DistanceCalculate distanceCalculate) {
         this.distanceCalculate = distanceCalculate;
     }

     public AbstractMap.SimpleEntry<Boolean, Double> can_proceed(Point2D.Double source, Point2D.Double destination) {
         Double distance = distanceCalculate.calculateDistance(source, destination);

         if (distance < 0) {
             throw new IllegalArgumentException("Mesafa negatif olamaz");
         }

         return new AbstractMap.SimpleEntry<>(distance <= MAX_WALKING_DISTANCE, distance);
     }
 }